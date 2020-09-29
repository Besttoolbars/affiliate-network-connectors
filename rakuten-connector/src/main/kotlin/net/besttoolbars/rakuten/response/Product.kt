package net.besttoolbars.rakuten.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "result")
data class RakutenProductRootResponse(
        @JacksonXmlProperty(localName = "TotalMatches")
        val totalMatches: Long? = null,
        @JacksonXmlProperty(localName = "TotalPages")
        val totalPages: Long? = null,
        @JacksonXmlProperty(localName = "PageNumber")
        val pageNumber: Long? = null,
        @JacksonXmlElementWrapper(useWrapping = false, localName = "item")
        val item: List<RakutenProductItem>? = null
)

data class RakutenProductItem(
        @JacksonXmlProperty(localName = "mid")
        val mid: Long? = null,
        @JacksonXmlProperty(localName = "merchantname")
        val merchantName: String? = null,
        @JacksonXmlProperty(localName = "linkid")
        val linkId: String? = null,
        @JacksonXmlProperty(localName = "createdon")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd/HH:mm:ss")
        val createOn: LocalDateTime? = null,
        @JacksonXmlProperty(localName = "sku")
        val sku: String? = null,
        @JacksonXmlProperty(localName = "productname")
        val productName: String? = null,
        @JacksonXmlProperty(localName = "category")
        val category: RakutenProductItemCategory? = null,
        @JacksonXmlProperty(localName = "price")
        val price: RakutenProductPrice? = null,
        @JacksonXmlProperty(localName = "saleprice")
        val salePrice: RakutenProductPrice? = null,
        @JacksonXmlProperty(localName = "upccode")
        val upcCode: String? = null,
        @JacksonXmlProperty(localName = "description")
        val description: RakutenProductDescription? = null,
        @JacksonXmlProperty(localName = "keywords")
        val keywords: String? = null,
        @JacksonXmlProperty(localName = "linkurl")
        val linkUrl: String? = null,
        @JacksonXmlProperty(localName = "imageurl")
        val imageUrl: String? = null
)

data class RakutenProductItemCategory(
        @JacksonXmlProperty(localName = "primary")
        val primary: String? = null,
        @JacksonXmlProperty(localName = "secondary")
        val secondary: String? = null
)

data class RakutenProductPrice(
        @JacksonXmlProperty(isAttribute = true, localName = "currency")
        val currency: String? = null
) {
        /**
         * JacksonXmlText doesn't work in constructors
         * https://github.com/FasterXML/jackson-module-kotlin/issues/138
         */
        @JacksonXmlText
        val value: Double? = null
}

data class RakutenProductDescription(
        @JacksonXmlProperty(localName = "short")
        val short: String? = null,
        @JacksonXmlProperty(localName = "long")
        val long: String? = null
)