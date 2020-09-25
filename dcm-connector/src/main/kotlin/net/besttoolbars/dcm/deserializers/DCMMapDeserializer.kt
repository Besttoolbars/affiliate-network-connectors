package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectReader
import net.besttoolbars.dcm.getDCMMapper

abstract class DCMMapDeserializer<K, V> : JsonDeserializer<Map<K, V>>() {
    abstract val reader: ObjectReader

    abstract fun read(p: JsonParser?): Map<K, V>

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Map<K, V> {
        return if (isArray(p)) {
            emptyMap()
        } else {
            return read(p)
        }
    }

    private fun isArray(p: JsonParser?): Boolean {
        return p?.isExpectedStartArrayToken ?: true
    }

    protected fun getReader(keyClass: Class<K>, valueClass: Class<V>): ObjectReader {
        val objectMapper = getDCMMapper()
        val mapType = objectMapper
            .getTypeFactory()
            .constructMapType(Map::class.java, keyClass, valueClass)
        return objectMapper.readerForMapOf(mapType.rawClass)
    }
}