package net.besttoolbars.lomadee.response

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import net.besttoolbars.lomadee.deserializer.LomadeeLocalDateDeserializer
import net.besttoolbars.lomadee.deserializer.LomadeeLocalDateTimeDeserializer
import java.time.LocalDate
import java.time.LocalDateTime

@JacksonXmlRootElement(localName = "result")
data class TransactionReport(
    val details: Details,
    val item: Item?
)

data class Details(
    @JsonDeserialize(using = LomadeeLocalDateTimeDeserializer::class)
    val timestamp: LocalDateTime,
    val code: Int,
    val elapsedTime: Int,
    val message: String,
    val status: String
)

data class Item(
    @JsonDeserialize(using = LomadeeLocalDateDeserializer::class)
    val date: LocalDate,
    val currency: String,
    val commission: Double,
    val associateId: String?,
    val site: Site,
    val advertiser: Advertiser,
    @field:JacksonXmlElementWrapper(useWrapping = true)
    val events: List<TransactionEvent>,
    val application: Application,
    val gmv: Double,
    val transactionCode: String,
    val transactionId: Int,
    val statusId: Int,
    val statusName: String,
    @JsonDeserialize(using = LomadeeLocalDateDeserializer::class)
    val processedDate: LocalDate,
    @JsonDeserialize(using = LomadeeLocalDateDeserializer::class)
    val clickDate: LocalDate,
    @JsonDeserialize(using = LomadeeLocalDateDeserializer::class)
    val checkoutDate: LocalDate
)

data class Site(
    val siteId: Int,
    val siteName: String
)

data class Advertiser(
    val advertiserId: Int,
    val advertiserName: String
)

data class Application(
    val applicationId: Int,
    val applicationName: String
)

data class TransactionEvent(
    val eventId: String,
    val eventName: String,
    val commission: Double,
    val gmv: Double
)