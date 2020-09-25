package net.besttoolbars.lomadee.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LomadeeLocalDateDeserializer : JsonDeserializer<LocalDate?>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate? {
        val result: String? = StringDeserializer.instance.deserialize(p, ctxt)
        if (result.isNullOrBlank()) {
            return null
        }
        return LocalDate.parse(result,
            formatter
        )
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }
}
