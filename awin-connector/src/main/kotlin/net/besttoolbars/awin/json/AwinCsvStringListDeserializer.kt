package net.besttoolbars.awin.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer

class AwinCsvStringListDeserializer: JsonDeserializer<List<String>>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): List<String> =
        StringDeserializer.instance.deserialize(p, ctxt)?.split(',').orEmpty()
}