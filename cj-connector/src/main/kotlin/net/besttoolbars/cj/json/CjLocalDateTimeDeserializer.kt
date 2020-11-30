package net.besttoolbars.cj.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CjLocalDateTimeDeserializer : JsonDeserializer<LocalDateTime?>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime? =
        StringDeserializer.instance.deserialize(p, ctxt).let { raw ->
            if (raw.isNullOrBlank()) null
            else {
                var result: LocalDateTime? = null
                for (formatter in formatters) {
                    try {
                        result = LocalDateTime.parse(raw, formatter)
                        break
                    } catch (e: DateTimeParseException) {
                        continue
                    }
                }
                if (result == null)
                    throw IllegalStateException("No patterns found for date: $raw")
                result
            }
        }

    companion object {
        private val formatters =
            listOf(
                "yyyy-MM-dd HH:mm:ss.S",
                "yyyy-MM-dd HH:mm:ss.SSS"
            ).map { DateTimeFormatter.ofPattern(it) }
    }
}

