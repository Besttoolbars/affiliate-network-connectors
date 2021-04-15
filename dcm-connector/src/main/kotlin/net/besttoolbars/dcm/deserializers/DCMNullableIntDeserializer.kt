package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class DCMNullableIntDeserializer : JsonDeserializer<Int?>() {
    override fun deserialize(jsonparser: JsonParser?, ctxt: DeserializationContext?): Int? {
        val value = jsonparser?.valueAsString
        val intValue = value?.toInt()
        return if (intValue == 0) null else intValue
    }
}