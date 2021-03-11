package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class DCMNullableNumberDeserializer : JsonDeserializer<Double?>() {
    override fun deserialize(jsonparser: JsonParser?, ctxt: DeserializationContext?): Double? {
        val value = jsonparser?.doubleValue
        return if (value == 0.0) null else value
    }
}