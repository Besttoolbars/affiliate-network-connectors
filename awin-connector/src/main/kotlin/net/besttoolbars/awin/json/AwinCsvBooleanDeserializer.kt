package net.besttoolbars.awin.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer

class AwinCsvBooleanDeserializer : JsonDeserializer<Boolean>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Boolean =
        StringDeserializer.instance.deserialize(p, ctxt)?.equals(TRUE_VALUE, ignoreCase = true) ?: false

    companion object {
        private const val TRUE_VALUE = "yes"
    }
}