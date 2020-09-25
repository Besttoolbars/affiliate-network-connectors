package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class DCMBooleanDeserializer : JsonDeserializer<Boolean>() {
    override fun deserialize(jsonparser: JsonParser?, ctxt: DeserializationContext?): Boolean {
        val value = jsonparser?.getText()
        return when (value) {
            "0" -> false
            "1" -> true
            else -> false
        }
    }
}