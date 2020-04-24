package dao

import com.google.gson.Gson
import gson
import model.Notice
import model.quiz.essential.ChapterSnap
import model.quiz.essential.SubjectSnap
import org.dizitart.kno2.nitrite
import java.awt.SystemTray
import java.io.File
import java.util.RandomAccess
import kotlin.random.Random

private val rootNotes = File("notes")
private val subjectsData by lazy {
    nitrite {
        file = File("subjectsData.db")
        compress = true
        autoCompact = true
    }
}
private val questionData by lazy {
    nitrite {
        file = File("questionsData.db")
        compress = true
        autoCompact = true
    }
}
private val questionsDataBase = QuestionsDataBase(subjectsData, questionData)


fun makeNotesDirs() {
    val folder = File("notes")
    folder.mkdirs()
    (6..12).map { it to questionsDataBase.getSubjectsRepo(it) }.forEach { classAndRepo ->
        val clazz = classAndRepo.first
        println("dao>>main  $clazz class ")
        classAndRepo.second.find().forEach { subjectSnap ->
            println("dao>>main  $subjectSnap ")
            val dir = File(folder, "$clazz/${subjectSnap.slug}")
            dir.mkdirs()
            questionsDataBase.getChaptersRepo(clazz, subjectSnap).find().forEach {
                val chapterDir = File(dir, it.slug)
                chapterDir.mkdirs()
                println("dao>>main  ${it.slug} ")
            }
        }
    }

}

fun randomNortices() {

        val id = System.currentTimeMillis() + Random.nextInt(23)
        val likesCount = Random.nextInt(123)
        val userImage = "https://randomuser.me/api/portraits/med/men/${likesCount%20}.jpg"
        val notice = Notice(id,
                "skjnskd", likesCount,"Ramput","hello "
                ,"${likesCount%24}th May", userImage,"Meghdut ")

    println("${Gson().toJson(notice)} ")

}

fun main() {
    val joinToString =
            (6..12).flatMap { questionsDataBase.getSubjectsRepo(it).find().toList() }.map { "${it.slug}=${it.name}" }.distinct().joinToString(";")
    println("dao>>main  $joinToString ")
}

private fun getFOlder(clazz: Int, subjectSnap: SubjectSnap, chapterSnap: ChapterSnap): File {
    val folder =
            File(rootNotes, "class_$clazz/${subjectSnap.slug}/${chapterSnap.slug}".replace("-", "_"))
    return folder
}