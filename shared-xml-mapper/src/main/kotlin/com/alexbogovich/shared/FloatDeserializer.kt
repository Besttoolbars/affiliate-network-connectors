package com.alexbogovich.shared

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer

object FloatDeserializer : JsonDeserializer<Float?>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Float? {
        val result: String? = StringDeserializer.instance.deserialize(p, ctxt)
        return result?.toFloatOrNull()
    }
}
