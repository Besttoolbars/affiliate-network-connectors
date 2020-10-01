package net.besttoolbars.connectors.shared

import com.ctc.wstx.api.InvalidCharHandler
import com.ctc.wstx.api.WstxInputProperties
import com.ctc.wstx.api.WstxOutputProperties
import com.ctc.wstx.stax.WstxInputFactory
import com.ctc.wstx.stax.WstxOutputFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object StringD : StdDeserializer<String>(String::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String? {
        val result = StringDeserializer.instance.deserialize(p, ctxt)
        return if (result.isNullOrBlank()) null else result.trim()
    }
}

fun provideXmlObjectMapper(): ObjectMapper {

    val xif = WstxInputFactory()
    xif.setProperty(WstxInputProperties.P_ALLOW_XML11_ESCAPED_CHARS_IN_XML10, true)

    val xof = WstxOutputFactory()
    xof.setProperty(WstxOutputProperties.P_OUTPUT_INVALID_CHAR_HANDLER, InvalidCharHandler.ReplacingHandler(' '))
    val woodstoxFactory = XmlFactory(xif, xof)

    val jacksonXmlModule = JacksonXmlModule().apply {
        setXMLTextElementName("xmlInnerText")
    }

    val xmlMapper = XmlMapper(woodstoxFactory, jacksonXmlModule).registerKotlinModule()
    val module = SimpleModule()
    module.addDeserializer(String::class.java, StringD)
    xmlMapper.registerModule(module)
    xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    xmlMapper.registerModule(JavaTimeModule())
    return xmlMapper
}
