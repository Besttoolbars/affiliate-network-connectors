package net.besttoolbars.pdlprofit.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer

class PdlProfitCreditPercentDeserializer : JsonDeserializer<Double>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Double =
        StringDeserializer.instance.deserialize(p, ctxt)
            ?.takeIf { it.isNotBlank() }
            ?.replace(',', '.')
            ?.toDoubleOrNull()
            ?: 0.0
}