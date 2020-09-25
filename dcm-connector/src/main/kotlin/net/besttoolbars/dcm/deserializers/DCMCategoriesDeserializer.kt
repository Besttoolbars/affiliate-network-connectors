package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.dto.DCMCategory

class DCMCategoriesDeserializer : DCMMapDeserializer<Int, DCMCategory>() {
    override val reader = getReader(Int::class.java, DCMCategory::class.java)
    override fun read(p: JsonParser?): Map<Int, DCMCategory> {
        return reader.readValueTyped(p!!)
    }
}

