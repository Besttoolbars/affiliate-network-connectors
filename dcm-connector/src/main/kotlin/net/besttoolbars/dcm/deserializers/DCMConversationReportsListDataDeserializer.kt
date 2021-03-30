package net.besttoolbars.dcm.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.readValueTyped
import net.besttoolbars.dcm.dto.DCMConversionReportData
import net.besttoolbars.dcm.dto.DCMReceiptsListData

class DCMConversationReportsListDataDeserializer : DCMMapDeserializer<String, DCMConversionReportData>() {
    override val reader = getReader(String::class.java, DCMConversionReportData::class.java)
    override fun read(p: JsonParser?): Map<String, DCMConversionReportData> {
        return reader.readValueTyped(p!!)
    }
}
