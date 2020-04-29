package com.alexbogovich.rakuten.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import java.time.LocalDate

data class RakutenCoupon(
    @field:JacksonXmlProperty(localName = "type", isAttribute = true)
    val type: String,

    @JacksonXmlProperty(localName = "offerdescription")
    val offerDescription: String,

    @JacksonXmlProperty(localName = "offerstartdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val offerStartDate: LocalDate,

    @JacksonXmlProperty(localName = "offerenddate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val offerEndDate: LocalDate,

    @JacksonXmlProperty(localName = "couponcode")
    val couponCode: String?,

    @JacksonXmlProperty(localName = "couponrestriction")
    val couponRestriction: String?,

    @JacksonXmlProperty(localName = "clickurl")
    val clickUrl: String,

    @JacksonXmlProperty(localName = "impressionpixel")
    val impressionPixel: String?,

    @JacksonXmlProperty(localName = "advertiserid")
    val advertiserId: Long,

    @JacksonXmlProperty(localName = "advertisername")
    val advertiserName: String,

    @JacksonXmlProperty
    val network: RakutenCouponNetwork,

    @JacksonXmlElementWrapper(localName = "categories", useWrapping = true)
    @JacksonXmlProperty(localName = "categories")
    val categories: List<RakutenCouponCategory> = emptyList(),

    @JacksonXmlElementWrapper(localName = "promotiontypes", useWrapping = true)
    @JacksonXmlProperty(localName = "promotiontypes")
    val promotionTypes: List<RakutenCouponPromotionType> = emptyList()
)

@JacksonXmlRootElement(localName = "couponfeed")
data class RakutenCouponResponse(
    @JacksonXmlProperty(localName = "TotalMatches")
    val totalMatch: Long,

    @JacksonXmlProperty(localName = "PageNumberRequested")
    val pageNumberRequested: Long,

    @JacksonXmlProperty(localName = "TotalPages")
    val totalPage: Long,

    @JacksonXmlElementWrapper(localName = "link", useWrapping = false)
    @JacksonXmlProperty(localName = "link")
    val links: List<RakutenCoupon> = emptyList()
)

sealed class RakutenBaseTag {
    @JacksonXmlProperty(isAttribute = true)
    lateinit var id: String

    @JacksonXmlText
    lateinit var value: String
}

class RakutenCouponCategory : RakutenBaseTag() {
    override fun toString() = "RakutenCouponCategory(id = $id, value = $value)"
}

class RakutenCouponNetwork : RakutenBaseTag() {
    override fun toString() = "RakutenCouponNetwork(id = $id, value = $value)"
}

class RakutenCouponPromotionType : RakutenBaseTag() {
    override fun toString() = "RakutenCouponPromotionType(id = $id, value = $value)"
}
