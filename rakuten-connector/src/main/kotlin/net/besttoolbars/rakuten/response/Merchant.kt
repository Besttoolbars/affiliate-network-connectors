package net.besttoolbars.rakuten.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

data class RakutenMerchant(
    @JacksonXmlProperty(namespace = "ns1")
    val applicationStatus: String,

    @JacksonXmlProperty(namespace = "ns1")
    val categories: String? = null,

    @JacksonXmlProperty(namespace = "ns1")
    val mid: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val name: String,

    @JacksonXmlProperty(namespace = "ns1")
    val offer: RakutenMerchantOffer
)

@JacksonXmlRootElement(namespace = "ns1", localName = "getMerchByAppStatusResponse")
data class RakutenMerchantByStatusResponse(
    @JacksonXmlElementWrapper(useWrapping = false, namespace = "ns1", localName = "return")
    @JacksonXmlProperty(namespace = "ns1", localName = "return")
    val merchants: List<RakutenMerchant> = listOf()
)

data class RakutenMerchantOffer(
    @JacksonXmlProperty(namespace = "ns1")
    val alsoName: String,

    @JacksonXmlProperty(namespace = "ns1")
    val commissionTerms: String,

    @JacksonXmlProperty(namespace = "ns1")
    val offerId: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val offerName: String
)

@JacksonXmlRootElement(localName = "result")
data class RakutenMerchantRootResponse(
    @JacksonXmlProperty(localName = "midlist")
    val midList: List<RakutenMerchantSearch>? = null
)

data class RakutenMerchantSearch(
    @JacksonXmlProperty(localName = "mid")
    val mid: Long? = null,
    @JacksonXmlProperty(localName = "merchantname")
    val merchantName: String? = null
)