import dao.NotesDao
import okhttp3.internal.closeQuietly
import org.apache.pdfbox.io.MemoryUsageSetting
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.PDFRenderer
import java.awt.image.BufferedImage
import java.io.File
import java.io.OutputStream
import javax.imageio.ImageIO


class NaivePdfCache(private val notesDao: NotesDao) : Runnable {

    private var lastRenderTime = -1

    class PdfStream(val id: String, val document: PDDocument) {
        val render: PDFRenderer
            get() {
                counter++
                lastAccessTime = System.currentTimeMillis()
                return PDFRenderer(document)
            }
        var lastAccessTime = System.currentTimeMillis()
        val unusedTime: Long
            get() = System.currentTimeMillis() - lastAccessTime
        var counter = 0
    }

    private val cacheMap = java.util.concurrent.ConcurrentHashMap<String, PdfStream>(5)
    private val dpi = 150
    private val fileExtension = "jpg"
    private val evictionTime = 5 * 60 * 1000

    @Suppress("UNUSED_VALUE")
    fun renderPage(outputStream: OutputStream, noteId: String, pageNo: Int) {

        val pdfStream = cacheMap.computeIfAbsent(noteId, ::getRenderer)
        var pdfRenderer: PDFRenderer? = pdfStream.render
        var renderImageWithDPI: BufferedImage? =
            pdfRenderer?.renderImageWithDPI(pageNo, dpi.toFloat(), ImageType.RGB)
        ImageIO.write(renderImageWithDPI, fileExtension, outputStream)
        renderImageWithDPI?.flush()
        renderImageWithDPI = null
        pdfRenderer = null
        if (pdfStream.counter % 5 == 0) {
            Thread {
                println(">NaivePdfCache>renderPage  Going for gc ")
                System.gc()
            }.start()
        }
    }


    fun hasPreview(noteId: String, pageNo: Int): Boolean {
        val previewFile = getPreviewFile(noteId, pageNo)
        return previewFile.exists()
    }

    fun getPreviewFile(noteId: String, pageNo: Int): File {
        val noteFOlder = notesDao.getNotesFolder(noteId)
        Thread {
            if (pageNo > 0)
                cacheMap.computeIfAbsent(noteId, ::getRenderer)
        }.start()
        return File(noteFOlder, "p$pageNo.jpg")
    }

    private fun evict(pdfStream: PdfStream) {
        pdfStream.document.closeQuietly()
        println(">NaivePdfCache>evict  Closing note ${pdfStream.id}  ")
        cacheMap.remove(pdfStream.id)
    }

    private fun clearCache() = Thread {
        cacheMap.values.filter { it.unusedTime > evictionTime }.forEach {
            evict(it)
        }
        System.gc()
    }.start()


    private fun getRenderer(noteId: String): PdfStream {
        val pdfFile = notesDao.getNotesFile(noteId)
        val setting = MemoryUsageSetting.setupTempFileOnly()
        val pdDocument = PDDocument.load(pdfFile, setting)
        val pdfStream = PdfStream(noteId, pdDocument)
        println(">NaivePdfCache>getRenderer Cacheing note $noteId  ")
        clearCache()
        return pdfStream
    }

    override fun run() {
        while (cacheMap.values.isNotEmpty()) {


            Thread.sleep(5 * 1000)
        }
    }
}