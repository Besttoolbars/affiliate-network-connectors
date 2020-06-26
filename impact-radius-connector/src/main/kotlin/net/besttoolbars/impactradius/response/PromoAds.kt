package net.besttoolbars.impactradius.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.LocalDateTime

@JacksonXmlRootElement(localName = "ImpactRadiusResponse")
data class ImpactRadiusPromoAdsResponse(
    @JacksonXmlProperty(localName = "PromotionalAds")
    val promoAdsWrapper: ImpactRadiusPromoAds
)

data class ImpactRadiusPromoAds(
    @JacksonXmlProperty(localName = "page", isAttribute = true)
    val page: Int,
    @JacksonXmlProperty(localName = "numpages", isAttribute = true)
    val numpages: Int,
    @JacksonXmlProperty(localName = "total", isAttribute = true)
    val total: Int,
    @JacksonXmlProperty(localName = "start", isAttribute = true)
    val start: Int,
    @JacksonXmlProperty(localName = "end", isAttribute = true)
    val end: Int,
    @JacksonXmlProperty(localName = "uri", isAttribute = true)
    val uri: String,
    @JacksonXmlProperty(localName = "firstpageuri", isAttribute = true)
    val firstpageuri: String?,
    @JacksonXmlProperty(localName = "previouspageuri", isAttribute = true)
    val previouspageuri: String?,
    @JacksonXmlProperty(localName = "nextpageuri", isAttribute = true)
    val nextpageuri: String?,
    @JacksonXmlProperty(localName = "lastpageuri", isAttribute = true)
    val lastpageuri: String?,
    @JacksonXmlProperty(localName = "PromotionalAd")
    @JacksonXmlElementWrapper(localName = "PromotionalAd", useWrapping = false)
    val promoAds: List<ImpactRadiusPromoAd> = listOf()
)

//<Id>564403</Id>
//<Name>Free Shipping on Orders $54.99+ at Crocs.ca! Offer valid until 12/31/19.</Name>
//<Description>Free Shipping on Orders $54.99+ at Crocs.ca! Offer valid until 12/31/19.</Description>
//<Status>ACTIVE</Status>
//<LinkText>Free Shipping on Orders $54.99+ at Crocs.ca! Offer valid until 12/31/19.</LinkText>
//<CampaignId>8121</CampaignId>
//<CampaignName>Crocs CA</CampaignName>
//<PromoType>FREESHIPPING</PromoType>
//<TrackingLink>//crocs-ca.xkri.net/c/1809157/564403/8121</TrackingLink>
//<LandingPageUrl>http://www.crocs.ca</LandingPageUrl>
//<AdHtml>&lt;h3&gt;&lt;a href="//crocs-ca.xkri.net/c/1809157/564403/8121"&gt;Free Shipping on Orders $54.99+ at Crocs.ca! Offer valid until 12/31/19.&lt;/a&gt;&lt;/h3&gt; &lt;img height="0" width="0" src="//crocs-ca.xkri.net/i/1809157/564403/8121" style="position:absolute;visibility:hidden;" border="0" /&gt;</AdHtml>
//<AdSize/>
//<ProductImageUrl/>
//<PromoCode/>
//<StartDate>2019-01-02T23:00:00-08:00</StartDate>
//<EndDate>2019-12-30T23:00:00-08:00</EndDate>
//<DiscountClassification>ENTIRESTORE</DiscountClassification>
//<DiscountType>FIXED</DiscountType>
//<BogoBuyQuantity>0</BogoBuyQuantity>
//<BogoGetQuantity>0</BogoGetQuantity>
//<PurchaseLimitQuantity>0</PurchaseLimitQuantity>
//<Language>ENGLISH</Language>

data class ImpactRadiusPromoAd(
    @JacksonXmlProperty(localName = "Id")
    val id: String,
    @JacksonXmlProperty(localName = "Name")
    val name: String,
    @JacksonXmlProperty(localName = "Description")
    val description: String?,
    @JacksonXmlProperty(localName = "Status")
    val status: String,
    @JacksonXmlProperty(localName = "LinkText")
    val linkText: String?,
    @JacksonXmlProperty(localName = "CampaignId")
    val campaignId: String,
    @JacksonXmlProperty(localName = "CampaignName")
    val campaignName: String,
    @JacksonXmlProperty(localName = "PromoType")
    val promoType: String?,
    @JacksonXmlProperty(localName = "TrackingLink")
    val trackingLink: String,
    @JacksonXmlProperty(localName = "LandingPageUrl")
    val landingPageUrl: String?,
    @JacksonXmlProperty(localName = "AdHtml")
    val adHtml: String,
    @JacksonXmlProperty(localName = "AdSize")
    val adSize: String?,
    @JacksonXmlProperty(localName = "PromoCode")
    val promoCode: String?,
    @JacksonXmlProperty(localName = "Language")
    val language: String,
    @JacksonXmlProperty(localName = "StartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val startDate: LocalDateTime?,
    @JacksonXmlProperty(localName = "EndDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val endDate: LocalDateTime?
)
