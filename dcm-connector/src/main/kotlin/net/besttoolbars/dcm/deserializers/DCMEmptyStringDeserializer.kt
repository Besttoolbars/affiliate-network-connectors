package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class DCMEmptyStringDeserializer : JsonDeserializer<String?>() {
    override fun deserialize(jsonparser: JsonParser?, ctxt: DeserializationContext?): String? {
        val value = jsonparser?.getText()
        return if (value.isNullOrBlank()) null else value
    }
}