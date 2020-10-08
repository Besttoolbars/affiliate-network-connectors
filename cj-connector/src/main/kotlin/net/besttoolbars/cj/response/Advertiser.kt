package net.besttoolbars.cj.response

import com.fasterxml.jackson.annotation.JsonIgnore
import net.besttoolbars.connectors.shared.FloatDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import net.besttoolbars.cj.CjCommissionRateType

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjAdvertiser(
    @field:JacksonXmlProperty(localName = "advertiser-id")
    val advertiserId: Int,

    @field:JacksonXmlProperty(localName = "account-status")
    val accountStatus: String?,

    @field:JacksonXmlProperty(localName = "seven-day-epc")
    @JsonDeserialize(using = FloatDeserializer::class)
    val sevenDayEpc: Float?,

    @field:JacksonXmlProperty(localName = "three-month-epc")
    @JsonDeserialize(using = FloatDeserializer::class)
    val threeMonthEpc: Float?,

    val language: String?,

    @field:JacksonXmlProperty(localName = "advertiser-name")
    val advertiserName: String?,

    @field:JacksonXmlProperty(localName = "program-url")
    val programUrl: String?,

    @field:JacksonXmlProperty(localName = "relationship-status")
    val relationshipStatus: String?,

    @field:JacksonXmlProperty(localName = "mobile-supported")
    val mobileSupported: Boolean?,

    @field:JacksonXmlProperty(localName = "mobile-tracking-certified")
    val mobileTrackingCertified: Boolean?,

    @field:JacksonXmlProperty(localName = "cookieless-tracking-enabled")
    val cookielessTrackingEnabled: Boolean?,

    @field:JacksonXmlProperty(localName = "network-rank")
    val networkRank: String?,

    @field:JacksonXmlProperty(localName = "primary-category")
    val primaryCategory: CjAdvertiserPrimaryCategory,

    @field:JacksonXmlProperty(localName = "performance-incentives")
    val performanceIncentives: Boolean?,

    @field:JacksonXmlElementWrapper(localName = "actions", useWrapping = true)
    val actions: List<CjAdvertiserAction>?,

    @field:JacksonXmlProperty(localName = "link-types")
    @field:JacksonXmlElementWrapper(localName = "link-types", useWrapping = true)
    val linkTypes: List<String>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjAdvertiserAction(
    val name: String,
    val type: String,
    val id: Int,
    val commission: CjActionCommission
)

data class CjAdvertiserPrimaryCategory(
    val parent: String?,
    val child: String
)

data class CjAdvertisers(
    @field:JacksonXmlProperty(localName = "total-matched", isAttribute = true)
    val total: Int,

    @field:JacksonXmlProperty(localName = "records-returned", isAttribute = true)
    val perPage: Int,

    @field:JacksonXmlProperty(localName = "page-number", isAttribute = true)
    val page: Int,

    @field:JacksonXmlElementWrapper(useWrapping = false)
    val advertiser: List<CjAdvertiser>
)

@JacksonXmlRootElement(localName = "cj-api")
data class CjAdvertisersResponse(
    val advertisers: CjAdvertisers
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjActionCommission(
    @field:JacksonXmlElementWrapper(localName = "itemlist", useWrapping = false)
    val itemlist: List<CjActionCommissionItem> = emptyList(),
    @field:JacksonXmlProperty(localName = "default")
    val default: CjActionCommissionDefault? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjActionCommissionItem(
    @field:JacksonXmlProperty(isAttribute = true)
    val name: String,
    @field:JacksonXmlProperty(isAttribute = true)
    val id: String,
    @field:JacksonXmlProperty(localName = "xmlInnerText")
    val text: String?
): CjCommissionRate(text.orEmpty())

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjActionCommissionDefault(
    @field:JacksonXmlProperty(localName = "xmlInnerText")
    val text: String = "0.0",
    @field:JacksonXmlProperty(localName = "type", isAttribute = true)
    val type: String? = null
): CjCommissionRate(text)

open class CjCommissionRate(value: String) {
    @delegate:JsonIgnore
    val amount by lazy { value.toSoftDouble() }
    @delegate:JsonIgnore
    val currency by lazy { value.filter { it.isLetter() } }

    @delegate:JsonIgnore
    val rateType by lazy {
        when {
            value.contains('%') -> CjCommissionRateType.PERCENT
            else -> CjCommissionRateType.FIXED
        }
    }

    private fun String.toSoftDouble(): Double = filter { it.isDigit() || it == '.' }.toDoubleOrNull() ?: 0.0
}