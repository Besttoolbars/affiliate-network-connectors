package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.getDCMMapper
import net.besttoolbars.dcm.dto.DCMError

class DCMErrorsDeserializer : JsonDeserializer<List<DCMError>>() {
    val listReader: ObjectReader
    val objectReader: ObjectReader

    init {
        val objectMapper = getDCMMapper()
        val listType = objectMapper
            .getTypeFactory()
            .constructCollectionType(List::class.java, DCMError::class.java)
        listReader = objectMapper.readerForListOf(listType.rawClass)

        objectReader = objectMapper.readerFor(DCMError::class.java)
    }

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): List<DCMError> {
        return if (isArray(p)) {
            listReader.readValueTyped(p!!)
        } else {
            val item = objectReader.readValueTyped<DCMError>(p!!)
            listOf(item)
        }
    }

    private fun isArray(p: JsonParser?): Boolean {
        return p?.isExpectedStartArrayToken ?: true
    }
}