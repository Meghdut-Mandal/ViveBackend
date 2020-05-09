import dao.NotesDao
import dao.UploadsDao
import model.Upload
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.PDFRenderer
import java.io.File
import java.util.concurrent.Executors
import javax.imageio.ImageIO


class ImageConverter(private val uploadsDao: UploadsDao, private val notesDao: NotesDao) {
    private val executor = Executors.newFixedThreadPool(2)
    private val fileExtension = "jpg"
    private val dpi = 150

    fun processUpload(uploadId: String) = executor.submit {
        var pdfFile: File? = null
        uploadsDao.updateStatus(uploadId, Upload.RECEIVED)
        try {
            val upload = uploadsDao.getUpload(uploadId)
            pdfFile = uploadsDao.getUploadFile(uploadId)
            val notePdfFile = notesDao.getNotesFile(upload.subjectTaughtID, upload.chapterName)
            val noteFolder = notePdfFile.parentFile

            copyPdf(pdfFile, notePdfFile)
            val numberOfPages = writePdfCovers(pdfFile, noteFolder)

            notesDao.addNote(upload.subjectTaughtID, upload.chapterName, numberOfPages)
            uploadsDao.updateStatus(uploadId, Upload.PROCESSED)
            pdfFile.delete()
        } catch (e: Exception) {
            pdfFile?.delete()
            e.printStackTrace()
            println(">ImageConverter>processUpload   ")
            uploadsDao.updateStatus(uploadId, Upload.ERROR)
        }
    }

    private fun copyPdf(pdfFile: File, notePdfFile: File) {
        pdfFile.inputStream().buffered().use { input ->
            notePdfFile.outputStream().buffered().use { out ->
                input.copyTo(out)
            }
        }
    }

    private fun writePdfCovers(pdfFile: File?, noteFolder: File?): Int {
        return PDDocument.load(pdfFile).use { document ->
            val pdfRenderer = PDFRenderer(document)
            val numberOfPages = if (document.numberOfPages > 3) 3 else document.numberOfPages
            (0..numberOfPages).forEach { index ->
                val outPutFile =
                    File(noteFolder, "p$index.$fileExtension")
                val bImage =
                    pdfRenderer.renderImageWithDPI(index, dpi.toFloat(), ImageType.RGB)
                outPutFile.outputStream().use { fileOutputStream ->
                    ImageIO.write(bImage, fileExtension, fileOutputStream)
                }
            }
            document.numberOfPages
        }
    }

}