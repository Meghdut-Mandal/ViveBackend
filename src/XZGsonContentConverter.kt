import io.ktor.application.ApplicationCall
import io.ktor.features.ContentConverter
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.request.ApplicationReceiveRequest
import io.ktor.util.pipeline.PipelineContext
import okhttp3.internal.closeQuietly
import org.tukaani.xz.LZMA2Options
import org.tukaani.xz.XZOutputStream
import java.io.ByteArrayOutputStream

class XZGsonContentConverter(val gsonConverter: GsonConverter = GsonConverter(gson)) : ContentConverter {
    override suspend fun convertForReceive(context: PipelineContext<ApplicationReceiveRequest, ApplicationCall>): Any? =
        gsonConverter.convertForReceive(context)

    override suspend fun convertForSend(
        context: PipelineContext<Any, ApplicationCall>,
        contentType: ContentType,
        value: Any
    ): Any? {
        val content = context.context.request.headers["Content-Negotiation"] ?: "None"
        return if (content == "compressed/xz") {
            val outputStream = ByteArrayOutputStream()
            val xzOutputStream = XZOutputStream(outputStream, LZMA2Options(8))
            xzOutputStream.write(gson.toJson(value).toByteArray())
            xzOutputStream.closeQuietly()
            outputStream.toByteArray()
        } else gsonConverter.convertForSend(context, contentType, value)

    }

}