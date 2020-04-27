package dao

import dao.generators.NCERTBookGenerator
import hash
import kotlinx.coroutines.flow.collect
import model.Book
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.Nitrite

interface BookDao {

    suspend fun reloadBookList() {
        NCERTBookGenerator.books().collect {
            insertBook(it.clazz, it.name, it.subject, it.querryPram)
        }
    }

    fun insertBook(clazz: Int, bookName: String, subject: String, querryPram: String): Boolean

    fun getBookByClass(clazz: Int): List<Book>

    fun getBookById(bookId: String): Book?
}

class BookDaoImpl(val nitrite: Nitrite) : BookDao {
    private val repository by lazy {
        nitrite.getRepository(Book::class.java)
    }


    override fun insertBook(clazz: Int, bookName: String, subject: String, querryPram: String): Boolean {
        val genBook = genBook(clazz, bookName, subject, querryPram)
        return if (repository.find(Book::id eq genBook.id).count() == 0) {
            repository.insert(genBook)
            true
        } else false
    }

    override fun getBookByClass(clazz: Int): List<Book> {
        return repository.find().filter { it.clazz == clazz }
    }


    override fun getBookById(bookId: String): Book? {
        return repository.find(Book::id eq bookId).firstOrNull()
    }

    companion object {
        fun bookHash(clazz: Int, bookName: String, subject: String, querryPram: String) =
            hash(clazz, bookName, subject, querryPram)

        fun genBook(clazz: Int, bookName: String, subject: String, querryPram: String): Book {
            val hash = bookHash(clazz, bookName, subject, querryPram)
            return Book(hash, clazz, bookName, subject, querryPram)
        }
    }

}
