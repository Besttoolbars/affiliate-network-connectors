package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.dto.DCMOfferUrlListData

class DCMOfferUrlListDataDeserializer : DCMMapDeserializer<String, DCMOfferUrlListData>() {
    override val reader = getReader(String::class.java, DCMOfferUrlListData::class.java)
    override fun read(p: JsonParser?): Map<String, DCMOfferUrlListData> {
        return reader.readValueTyped(p!!)
    }
}
