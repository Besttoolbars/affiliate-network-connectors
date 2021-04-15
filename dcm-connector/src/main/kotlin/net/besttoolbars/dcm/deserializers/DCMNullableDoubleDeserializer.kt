package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class DCMNullableDoubleDeserializer : JsonDeserializer<Double?>() {
    override fun deserialize(jsonparser: JsonParser?, ctxt: DeserializationContext?): Double? {
        val value = jsonparser?.valueAsString
        val doubleValue = value?.toDouble()
        return if (doubleValue == 0.0) null else doubleValue
    }
}