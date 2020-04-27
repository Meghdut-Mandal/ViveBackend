package dao.generators

import org.jsoup.Jsoup


object DAVBookGenerator {

    fun loadBooks() {
     val doc=Jsoup.connect("http://gddavdeoghar.org/A27050A7-DDF8-414D-8E46-85E1F6172852/CMS/Page/E---BOOK").execute().parse()
     doc.select("tr")

    }
}

fun main() {
 DAVBookGenerator.loadBooks()
}