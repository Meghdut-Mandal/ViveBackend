import dao.*
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import java.io.File

/**
 * Constructs a facade with the database, connected to the DataSource configured earlier with the [dir]
 * for storing the database.
 */
 val dao: ViveDao =
    DAONitrateDataBase(File("data/data.db"))
 val schoolsDao = SchoolListDAO(File("data/edugorrilas.db"))

/**
 * Entry Point of the application. This function is referenced in the
 * resources/application.conf file inside the ktor.application.modules.
 *
 * For more information about this file: https://ktor.io/servers/configuration.html#hocon-file
 */
 val subjectsData = getDb("subjectsData")
private  fun getDb(fileName: String): Nitrite =
    nitrite {
        file = File("data/$fileName.db")
        compress = true
        autoCompact = true
    }

private val questionData = getDb("questionsData")
private val attendanceData = getDb("attendance")
private val teachersDao = getDb("teacher")
private val uploadsData = getDb("uploads")
private val notesData = getDb("notes")
private val booksData = getDb("books")

private val subjectTaughtData = getDb("subjects_taught")
val subjectsTaughtDao = SubjectTaughtDaoImpl(subjectTaughtData)
val uploadsDao = UploadDaoImpl(uploadsData)
val bookDao = BookDaoImpl(booksData)
val attendanceDAO = AttendanceDAO(attendanceData)
val teacherDao: TeacherDao = TeacherDaoImpl(teachersDao)
val questionsDataBase = QuestionsDataBase(subjectsData, questionData)
val notesDao =
    NotesDao(notesData, subjectsData, File("notes"))