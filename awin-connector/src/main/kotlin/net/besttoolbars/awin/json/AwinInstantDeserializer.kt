package net.besttoolbars.awin.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AwinInstantDeserializer : JsonDeserializer<Instant>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Instant? =
        StringDeserializer.instance.deserialize(p, ctxt)
            ?.takeUnless { it.isBlank() }
            ?.let { formatter.parse(it, Instant::from) }

    companion object {
        private val formatter by lazy {
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.of("UTC"))
        }
    }
}