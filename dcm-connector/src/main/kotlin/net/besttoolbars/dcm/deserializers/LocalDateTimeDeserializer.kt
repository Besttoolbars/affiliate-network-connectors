package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime?>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun deserialize(jsonparser: JsonParser?, ctxt: DeserializationContext?): LocalDateTime? {
        val value = jsonparser?.getText()
        return try {
            LocalDateTime.parse(value, formatter)
        } catch (e: Exception) {
            null
        }
    }
}