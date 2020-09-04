package net.besttoolbars.cj.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import java.time.Instant

class CjInstantDeserializer : JsonDeserializer<Instant?>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Instant? {
        val result: String? = StringDeserializer.instance.deserialize(p, ctxt)
        if (result.isNullOrBlank()) {
            return null
        }
        return Instant.parse(result)
    }
}