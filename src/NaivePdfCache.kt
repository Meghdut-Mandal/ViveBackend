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


class NaivePdfCache(private val notesDao: NotesDao) {

    private class PdfStream(val id: String, private val rend: PDFRenderer, val document: PDDocument) {
        val render: PDFRenderer
            get() {
                lastAccessTime = System.currentTimeMillis()
                return rend
            }
        var lastAccessTime = System.currentTimeMillis()
        val unusedTime: Long
            get() = System.currentTimeMillis() - lastAccessTime
    }

    private val cacheMap = java.util.concurrent.ConcurrentHashMap<String,PdfStream>(5)
    private val dpi = 150
    private val fileExtension = "jpg"
    private val evictionTime = 5 * 60 * 1000


    fun renderPage(outputStream: OutputStream, noteId: String, pageNo: Int) {
        val pdfRenderer = cacheMap.computeIfAbsent(noteId, ::getRenderer).render
        var renderImageWithDPI:BufferedImage? =
            pdfRenderer.renderImageWithDPI(pageNo, dpi.toFloat(), ImageType.RGB)
        ImageIO.write(renderImageWithDPI, fileExtension, outputStream)
        renderImageWithDPI?.flush()
        @Suppress("UNUSED_VALUE")
        renderImageWithDPI=null
    }


    fun hasPreview(noteId: String, pageNo: Int): Boolean {
        val previewFile = getPreviewFile(noteId, pageNo)
        return previewFile.exists()
    }

    fun getPreviewFile(noteId: String, pageNo: Int): File {
        val noteFOlder = notesDao.getNotesFolder(noteId)
        Thread{
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
        val render = PDFRenderer(pdDocument)
        val pdfStream = PdfStream(noteId, render, pdDocument)
        println(">NaivePdfCache>getRenderer Cacheing note $noteId  ")
        clearCache()
        return pdfStream
    }
}