package com.alexbogovich.rakuten.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.LocalDate

data class RakutenBannerLink(
    @JacksonXmlProperty(namespace = "ns1")
    val campaignID: String,

    @JacksonXmlProperty(namespace = "ns1")
    val categoryID: String,

    @JacksonXmlProperty(namespace = "ns1")
    val categoryName: String?,

    @JacksonXmlProperty(namespace = "ns1")
    val linkID: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val linkName: String,

    @JacksonXmlProperty(namespace = "ns1")
    val mid: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val nid: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val clickURL: String,

    @JacksonXmlProperty(namespace = "ns1")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "LLL dd, yyyy")
    val endDate: LocalDate,

    @JacksonXmlProperty(namespace = "ns1")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "LLL dd, yyyy")
    val startDate: LocalDate,

    @JacksonXmlProperty(namespace = "ns1")
    val landURL: String?,

    @JacksonXmlProperty(namespace = "ns1")
    val iconURL: String,

    @JacksonXmlProperty(namespace = "ns1")
    val imgURL: String,

    @JacksonXmlProperty(namespace = "ns1")
    val showURL: String?,

    @JacksonXmlProperty(namespace = "ns1")
    val height: Int,

    @JacksonXmlProperty(namespace = "ns1")
    val width: Int,

    @JacksonXmlProperty(namespace = "ns1")
    val size: Int,

    @JacksonXmlProperty(namespace = "ns1")
    val serverType: Int
)

@JacksonXmlRootElement(namespace = "ns1", localName = "getBannerLinksResponse")
data class RakutenBannerResponse(
    @JacksonXmlElementWrapper(useWrapping = false, namespace = "ns1", localName = "return")
    @JacksonXmlProperty(namespace = "ns1", localName = "return")
    val bannerLinks: List<RakutenBannerLink> = listOf()
)

data class RakutenTextLink(
    @JacksonXmlProperty(namespace = "ns1")
    val campaignID: String,

    @JacksonXmlProperty(namespace = "ns1")
    val categoryID: String,

    @JacksonXmlProperty(namespace = "ns1")
    val categoryName: String?,

    @JacksonXmlProperty(namespace = "ns1")
    val linkID: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val linkName: String,

    @JacksonXmlProperty(namespace = "ns1")
    val mid: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val nid: Long,

    @JacksonXmlProperty(namespace = "ns1")
    val clickURL: String,

    @JacksonXmlProperty(namespace = "ns1")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "LLL dd, yyyy")
    val endDate: LocalDate,

    @JacksonXmlProperty(namespace = "ns1")
    val landURL: String?,

    @JacksonXmlProperty(namespace = "ns1")
    val showURL: String?,

    @JacksonXmlProperty(namespace = "ns1")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "LLL dd, yyyy")
    val startDate: LocalDate,

    @JacksonXmlProperty(namespace = "ns1")
    val textDisplay: String
)

@JacksonXmlRootElement(namespace = "ns1", localName = "getTextLinksResponse")
data class RakutenTextLinkResponse(
    @JacksonXmlElementWrapper(useWrapping = false, namespace = "ns1", localName = "return")
    @JacksonXmlProperty(namespace = "ns1", localName = "return")
    val textLinks: List<RakutenTextLink> = listOf()
)
