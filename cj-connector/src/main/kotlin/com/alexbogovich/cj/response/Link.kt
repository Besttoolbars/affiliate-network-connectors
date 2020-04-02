package com.alexbogovich.cj.response

import com.alexbogovich.cj.json.CjLocalDateTimeDeserializer
import com.alexbogovich.shared.FloatDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.LocalDateTime

/**
 *
 * @property advertiserId The advertiser's CID.
 * @property clickCommission The commission per click offered by the advertiser.
 * @property creativeHeight The height (in pixels) of the link.
 * @property creativeWidth The width (in pixels) of the link.
 * @property leadCommission The commission per lead offered by the advertiser.
 * @property linkCodeHtml The link code (in HTML). This field is blank for advertisers with which you do not have a relationship (non-joined).
 * @property linkCodeJavascript The link code (in JavaScript). Note: This field is blank for advertisers with which you do not have a relationship (non-joined) or advanced links..
 * @property destination The link's destination URL.
 * @property description The link's description.
 * @property linkId The link ID.
 * @property linkName The link name.
 * @property linkType The link type.
 * @property advertiserName The advertisers name.
 * @property performanceIncentive Whether or not the advertiser offers performance incentives. This value is "Yes" if they do, or "No" if they do not offer incentives.
 * @property promotionType The associated promotion type.
 * @property promotionStartDate The associated promotion start date.
 * @property promotionEndDate The associated promotion end date.
 * @property relationshipStatus The advertisers relationship status with a publisher. This value can be either joined or not- joined.
 * @property saleCommission The commission per sale offered by the advertiser.
 * @property sevenDayEpc The link's 7-day EPC.
 * @property threeMonthEpc The link's 3-month EPC.
 * @property clickUrl The CJ tracking URL.
 * @property category Category of offer.
 * @property language Language of offer.
 * @property couponCode Coupon code (if present)
 *
 */
data class CjLink(
    @JacksonXmlProperty(localName = "advertiser-id")
    val advertiserId: Int,

    @JacksonXmlProperty(localName = "click-commission")
    @JsonDeserialize(using = FloatDeserializer::class)
    val clickCommission: Float?,

    @JacksonXmlProperty(localName = "creative-height")
    val creativeHeight: Int?,

    @JacksonXmlProperty(localName = "creative-width")
    val creativeWidth: Int?,

    @JacksonXmlProperty(localName = "lead-commission")
    @JsonDeserialize(using = FloatDeserializer::class)
    val leadCommission: Float?,

    @JacksonXmlProperty(localName = "link-code-html")
    val linkCodeHtml: String?,

    @JacksonXmlProperty(localName = "link-code-javascript")
    val linkCodeJavascript: String?,

    val destination: String?,

    val description: String?,

    @JacksonXmlProperty(localName = "link-id")
    val linkId: Int,

    @JacksonXmlProperty(localName = "link-name")
    val linkName: String?,

    @JacksonXmlProperty(localName = "link-type")
    val linkType: String?,

    @JacksonXmlProperty(localName = "advertiser-name")
    val advertiserName: String?,

    @JacksonXmlProperty(localName = "performance-incentive")
    val performanceIncentive: Boolean?,

    @JacksonXmlProperty(localName = "promotion-type")
    val promotionType: String?,

    @JacksonXmlProperty(localName = "promotion-start-date")
    @JsonDeserialize(using = CjLocalDateTimeDeserializer::class)
    val promotionStartDate: LocalDateTime?,

    @JacksonXmlProperty(localName = "promotion-end-date")
    @JsonDeserialize(using = CjLocalDateTimeDeserializer::class)
    val promotionEndDate: LocalDateTime?,

    @JacksonXmlProperty(localName = "relationship-status")
    val relationshipStatus: String?,

    @JacksonXmlProperty(localName = "sale-commission")
    val saleCommission: String?,

    @JacksonXmlProperty(localName = "seven-day-epc")
    @JsonDeserialize(using = FloatDeserializer::class)
    val sevenDayEpc: Float?,

    @JacksonXmlProperty(localName = "three-month-epc")
    @JsonDeserialize(using = FloatDeserializer::class)
    val threeMonthEpc: Float?,

    val clickUrl: String?,

    val category: String?,

    val language: String,

    @JacksonXmlProperty(localName = "coupon-code")
    val couponCode: String?
)

data class CjLinks(
    @field:JacksonXmlProperty(localName = "total-matched", isAttribute = true)
    val total: Int,

    @field:JacksonXmlProperty(localName = "records-returned", isAttribute = true)
    val recordsReturned: Int,

    @field:JacksonXmlProperty(localName = "page-number", isAttribute = true)
    val page: Int,

    @field:JacksonXmlElementWrapper(useWrapping = false)
    val link: List<CjLink>
)

@JacksonXmlRootElement(localName = "cj-api")
data class CjLinksResponse(
    val links: CjLinks
)
