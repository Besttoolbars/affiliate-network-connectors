package net.besttoolbars.rakuten.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import java.time.LocalDateTime

data class RakutenProduct(
    val mid: String,

    @JacksonXmlProperty(localName = "merchantname")
    val merchant: String,

    @JacksonXmlProperty(localName = "createdon")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd/hh:mm:ss")
    val create: LocalDateTime,

    val sku: String,

    @JacksonXmlProperty(localName = "productname")
    val name: String,

    val category: RakutenProductCategory,

    val price: RakutenProductPrice,

    @JacksonXmlProperty(localName = "upccode")
    val upc: Long,

    @JacksonXmlProperty(localName = "long")
    val description: String,

    @JacksonXmlProperty(localName = "linkurl")
    val link: String,

    @JacksonXmlProperty(localName = "imageurl")
    val image: String

)

@JacksonXmlRootElement(localName = "result")
data class RakutenProductResponse(
    val item: RakutenProduct
)

data class RakutenProductCategory (
    val primary: String,

    val secondary: String
)

data class RakutenProductPrice(
    @JacksonXmlProperty(isAttribute = true)
    val currency: String,

    @JacksonXmlText
    val value: Double
)