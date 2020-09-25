package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.dto.DCMOfferListData

class DCMOfferListDataDeserializer : DCMMapDeserializer<String, DCMOfferListData>() {
    override val reader = getReader(String::class.java, DCMOfferListData::class.java)
    override fun read(p: JsonParser?): Map<String, DCMOfferListData> {
        return reader.readValueTyped(p!!)
    }
}
