package routes

import NaivePdfCache
import NotePageRequest
import StudentAPI
import dao.BookDao
import dao.NotesDao
import dao.SubjectTaughtDao
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.LocalFileContent
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.response.respondOutputStream
import io.ktor.routing.Route
import model.Batch
import model.StringResponse
import okhttp3.internal.closeQuietly
import org.apache.http.entity.ContentType
import java.io.File

fun Route.student(
    bookDao: BookDao,
    subjectTaughtDao: SubjectTaughtDao,
    notesDao: NotesDao, naivePdfCache: NaivePdfCache,
    subjectImageDir: File = File("sub_image")
) {

    post<StudentAPI.Subjects> {
        val batch = call.receive<Batch>()
        val subjects = subjectTaughtDao.getSubjects(batch)
        return@post call.respond(subjects)
    }

    get<StudentAPI.Notes> {
        val id = it.subject_id
        val notes = notesDao.getNotes(id)
        call.respond(notes)
    }
    get<StudentAPI.Subjects.Image> {
        val file = File(subjectImageDir, "${it.slug}.svg")
        if (file.exists())
            call.respond(LocalFileContent(file))
        else call.respond(LocalFileContent(File(subjectImageDir, "default.svg")))
    }


    get<NotePageRequest> {
        val file = notesDao.getNotesFile(it.id)
        if (file.exists()) {

            if (naivePdfCache.hasPreview(it.id,it.pageno))
            {
                call.respondFile(naivePdfCache.getPreviewFile(it.id,it.pageno))
            }else {
                call.respondOutputStream(io.ktor.http.ContentType.Image.JPEG) {
                    naivePdfCache.renderPage(this, it.id, it.pageno)
                    closeQuietly()
                }
            }


        } else call.respond(HttpStatusCode.NotFound, StringResponse(404, "Page not found"))
    }

    get<StudentAPI.LoadBooks> {
        bookDao.reloadBookList()
        call.respond(StringResponse(200, "Done"))
    }

    get<StudentAPI.Books> {
        val bookByClass = bookDao.getBookByClass(it.clazz)
        return@get call.respond(bookByClass)
    }

}
