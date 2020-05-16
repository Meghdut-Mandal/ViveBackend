import dao.NotesDao
import org.ghost4j.document.PDFDocument
import org.ghost4j.renderer.SimpleRenderer
import java.awt.image.RenderedImage
import java.io.File
import java.io.OutputStream
import java.util.concurrent.atomic.AtomicInteger
import javax.imageio.ImageIO


class GhostScriptImageCache(private val notesDao: NotesDao) {
    private val evictionTime = 5 * 60 * 1000
    private val renderCount = AtomicInteger(0)



    private fun launchGC() {
        if (renderCount.compareAndSet(1, 2)) {
            println(">GhostScriptImageCache>launchGC  Started GC ")
            Thread{
                System.gc()
                Thread.sleep(500)
                println(">GhostScriptImageCache>launchGC  GC Done ! ")
                renderCount.compareAndSet(2,0)
            }.start()
        }else{
            println(">GhostScriptImageCache>launchGC  GC Already  in Progress ")
        }
    }

    private class PdfEntry(val id: String, val document: PDFDocument) {

        private val dpi = 150
        private val fileExtension = "jpg"

        private val renderer: SimpleRenderer = SimpleRenderer()

        fun renderPage(outputStream: OutputStream, pageNo: Int) {
            var renderedImage: RenderedImage? = renderer.render(document, pageNo, pageNo)[0] as RenderedImage
//            outputStream.use { stream ->
                ImageIO.write(renderedImage, fileExtension, outputStream)
//            }
            renderedImage = null
        }


        var lastAccessTime = System.currentTimeMillis()
        val unusedTime: Long
            get() = System.currentTimeMillis() - lastAccessTime
        var counter = 0
    }


    private val cacheMap = java.util.concurrent.ConcurrentHashMap<String, PdfEntry>(5)


    fun renderPage(outputStream: OutputStream, noteId: String, pageNo: Int) {
        val pdfEntry = cacheMap.computeIfAbsent(noteId, ::getRenderer)
        pdfEntry.renderPage(outputStream, pageNo)
        renderCount.compareAndSet(0,1)
        launchGC()
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

    private fun evict(pdfStream: PdfEntry) {
        println(">NaivePdfCache>evict  Closing note ${pdfStream.id}  ")
        cacheMap.remove(pdfStream.id)
    }

    private fun getRenderer(noteId: String): PdfEntry {
        val pdfFile = notesDao.getNotesFile(noteId)
        val document = PDFDocument()
        pdfFile.inputStream().buffered().use { stream ->
            document.load(stream)
        }
        return PdfEntry(noteId, document)
    }


}