package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.dto.DCMReceiptsListData

class DCMReceiptsListDataDeserializer : DCMMapDeserializer<String, DCMReceiptsListData>() {
    override val reader = getReader(String::class.java, DCMReceiptsListData::class.java)
    override fun read(p: JsonParser?): Map<String, DCMReceiptsListData> {
        return reader.readValueTyped(p!!)
    }
}
