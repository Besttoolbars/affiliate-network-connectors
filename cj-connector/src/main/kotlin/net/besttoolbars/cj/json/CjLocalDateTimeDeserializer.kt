package net.besttoolbars.cj.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CjLocalDateTimeDeserializer : JsonDeserializer<LocalDateTime?>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime? {
        val result: String? = StringDeserializer.instance.deserialize(p, ctxt)
        if (result.isNullOrBlank()) {
            return null
        }
        return LocalDateTime.parse(result,
            formatter
        )
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
    }
}

