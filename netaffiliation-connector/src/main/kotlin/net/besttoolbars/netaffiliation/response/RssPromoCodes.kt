package net.besttoolbars.netaffiliation.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "root")
data class RssPromoCodes(
    val channel: PromoCodesChannel
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PromoCodesChannel(
    val title: String,
    val link: String,
    val description: String,
    @JacksonXmlElementWrapper(useWrapping=false)
    val item: List<PromoCode>,
    val langue: String? = null,
    val pubDate: String? = null,
    val lastBuildDate: String? = null,
    val ttl: Int? = null,
    val image: RssImage? = null
)

data class RssImage(
    val url: String,
    val link: String,
    val title: String,
    val width: Int,
    val height: Int
)

data class PromoCode(
    val link: String,
    val title: String,
    val description: String,
    val code: String?,
    @JacksonXmlProperty(localName = "startdate")
    val startDate: String?,
    @JacksonXmlProperty(localName = "enddate")
    val endDate: String?,
    val timezone: String?,
    @JacksonXmlProperty(localName = "idcamp")
    val idCamp: Int,
    @JacksonXmlProperty(localName = "datemod")
    val dateMod: String
)