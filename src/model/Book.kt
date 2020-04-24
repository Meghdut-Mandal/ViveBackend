package model

import org.dizitart.no2.objects.Id

data class Book(
    @Id val id: String,
    val clazz: Int,
    val name: String,
    val subject: String,
    val querryPram: String
) {
    val previewUrl: String
        get() = "http://ncert.nic.in/textbook/pdf/${querryPram.substringBefore("=")}cc.jpg"

    val chapters: List<String>
        get() {
            val chapterCount = querryPram.substringAfter("=").split("-")[1].toInt()
            return (1..chapterCount).map {
                "http://ncert.nic.in/textbook/pdf/${querryPram.substringBefore("=")}${String.format(
                    "%02d",
                    it
                )}.pdf"
            }
        }
}



