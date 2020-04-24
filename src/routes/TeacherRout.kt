package routes

import ImageConverter
import TeacherAPI
import TeacherAPI.SignUpPage
import auth.JwtConfig
import auth.Token
import authFail
import com.google.gson.Gson
import dao.NotesDao
import dao.SubjectTaughtDao
import dao.TeacherDao
import dao.UploadsDao
import gson
import hash
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.thymeleaf.ThymeleafContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import model.StringResponse
import model.SubjectTaught
import model.Teacher
import model.Upload
import withTeacher
import java.io.InputStream
import java.io.OutputStream

val stringResponseError = StringResponse(300, "Error in parameters")

fun Route.teachers(
    imageConverter: ImageConverter,
    teacherDao: TeacherDao,
    uploadsDao: UploadsDao,
    subjectTaughtDao: SubjectTaughtDao,
    notesDao: NotesDao
) {

    post<TeacherAPI.LogInRequest> {
        val passwordCredential = call.receive<UserPasswordCredential>()
        val email = passwordCredential.name
        val password = passwordCredential.password
        if (teacherDao.hasTeacher(passwordCredential.name)) {
            val teacher = teacherDao.getTeacher(passwordCredential.name)!!
            val calculated = hash(email, password)
            if (teacher.hash == calculated) {
                call.respond(StringResponse(0, JwtConfig.makeToken(teacher)))
            } else {
                call.respond(StringResponse(2, "Incorrect Password"))
            }
        } else {
            call.respond(StringResponse(1, "Teacher not found"))
        }
    }


    get<SignUpPage> {
        call.respond(ThymeleafContent("teacher_signup", mapOf()))
    }

    post<SignUpPage> {
        val post = call.receive<Parameters>()
        val userName = post["name"]
            ?: return@post call.respond(
                ThymeleafContent(
                    "teacher_signup",
                    mapOf("error" to "The name Should not be empty")
                )
            )
        val userEmail = post["email"]
            ?: return@post call.respond(
                ThymeleafContent(
                    "teacher_signup",
                    mapOf("error" to "The Email Should not be empty")
                )
            )
        val userPassword = post["psw"]
            ?: return@post call.respond(
                ThymeleafContent(
                    "teacher_signup",
                    mapOf("error" to "The Password Should not be empty")
                )
            )
        if (teacherDao.hasTeacher(userEmail)) {
            return@post call.respond(
                ThymeleafContent(
                    "teacher_signup",
                    mapOf("error" to "The email has been already used. Try some other Email.")
                )
            )
        } else {
            val teacher =
                Teacher(userEmail, hash(userEmail, userPassword), userName, arrayListOf())
            val sucess = teacherDao.addTeacher(teacher)
            if (sucess)
                call.respond(ThymeleafContent("registration_sucesspage", mapOf()))
            else call.respond("Some internal error is there !")
        }
    }



    get<TeacherAPI.Remove> {
        call.respond(ThymeleafContent("remove_teacher", mapOf()))
    }

    authenticate {


        get<TeacherAPI.Uploads> {
            withTeacher()?.let { teacher ->
                val notes = teacher.subjects.flatMap { notesDao.getNotes(it) }
                call.respond(HttpStatusCode.OK, notes)
            } ?: authFail()
        }

        get<TeacherAPI.RecentUploads> {
            withTeacher()?.let {
                val recentUploads = uploadsDao.getRecentUploads(it.id)
                call.respond(recentUploads)
            } ?: authFail()
        }

        get<TeacherAPI.Info> {
            withTeacher()?.let {
                call.respond(it.copy(hash = ""))
            } ?: authFail()
        }


        post<TeacherAPI.UploadID> {
            withTeacher()?.let {
                val post = call.receive<Parameters>()
                val taught = post["taughtby"] ?: return@post call.respond(stringResponseError)
                val chapterName = post["chaptername"] ?: return@post call.respond(stringResponseError)
                val (_, batch, subjectName, subjectSlug) = Gson().fromJson(taught, SubjectTaught::class.java)
                val subjectID =
                    subjectTaughtDao.addSubject(batch, subjectName, subjectSlug)
                teacherDao.addSubject(it.id, subjectID)
                val upload = uploadsDao.addUpload(it.id, subjectID, chapterName)
                return@post call.respond(StringResponse(200, upload))
            } ?: authFail()
        }

        post<TeacherAPI.UploadNotes> {
            withTeacher()?.let { teacher ->
                val multipart = call.receiveMultipart()
                val uploadId: String = it.upload_id
                if (!uploadsDao.hasUpload(uploadId))
                    return@post call.respond(StringResponse(300, "Invalid Upload Id"))
                println("routes>>teachers  Upload id ${it.upload_id} ")
                val upload = uploadsDao.getUpload(uploadId)
                if (upload.teacherID!=teacher.id)
                    return@post authFail()
                val file = uploadsDao.getUploadFile(uploadId)
                file.delete()
                multipart.forEachPart { part ->
                    if (part is PartData.FileItem) {
                        part.streamProvider().use { its ->
                            println("routes>>teachers  stream file started  ")
                            // copy the stream to the file with buffering
                            file.outputStream().buffered().use {
                                // note that this is blocking
                                call.respond(StringResponse(200, "Successfully uploaded "))
                                its.copyTo(it)
                                println("routes>>teachers stream complete ")
                                uploadsDao.updateStatus(uploadId, Upload.RECEIVED)
                                imageConverter.processUpload(uploadId)
                                println("routes>>teachers conversion launched  ")

                            }
                        }
                    }
                    part.dispose()
                    println("routes>>teachers  Part disposed  ")
                }
                println("routes>>teachers  reponding back ")
            } ?: authFail()
        }


    }

    get<TeacherAPI.UploadNotes> {
        println("routes>>teachers   ")
        if (uploadsDao.hasUpload(it.upload_id)) {
            val upload = uploadsDao.getUpload(it.upload_id)
            return@get call.respond(upload)
        } else return@get call.respond(StringResponse(300, "Invaid Upload ID"))
    }

    get<TeacherAPI.StartConversion> {
        println("routes>>teachers  Note converison request ${it.upload_id} ")
        val uploadId: String = it.upload_id
        val file = uploadsDao.getUploadFile(uploadId)
        if (file.exists()) {
            imageConverter.processUpload(uploadId)
            call.respond(StringResponse(200, "Successful"))
        } else call.respond(StringResponse(300, "File not Exist"))
    }

}

suspend fun InputStream.copyToSuspend(
    out: OutputStream,
    bufferSize: Int = DEFAULT_BUFFER_SIZE,
    yieldSize: Int = 4 * 1024 * 1024,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): Long {
    return withContext(dispatcher) {
        val buffer = ByteArray(bufferSize)
        var bytesCopied = 0L
        var bytesAfterYield = 0L
        while (true) {
            val bytes = read(buffer).takeIf { it >= 0 } ?: break
            out.write(buffer, 0, bytes)
            if (bytesAfterYield >= yieldSize) {
                yield()
                bytesAfterYield %= yieldSize
            }
            bytesCopied += bytes
            bytesAfterYield += bytes
        }
        return@withContext bytesCopied
    }
}
