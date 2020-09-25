package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.dto.DCMOfferFileListData

class DCMOfferFileListDataDeserializer : DCMMapDeserializer<String, DCMOfferFileListData>() {
    override val reader = getReader(String::class.java, DCMOfferFileListData::class.java)
    override fun read(p: JsonParser?): Map<String, DCMOfferFileListData> {
        return reader.readValueTyped(p!!)
    }
}
