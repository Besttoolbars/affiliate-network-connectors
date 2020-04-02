package com.alexbogovich.cj.response

import com.alexbogovich.shared.FloatDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

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

    val id: Int
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
