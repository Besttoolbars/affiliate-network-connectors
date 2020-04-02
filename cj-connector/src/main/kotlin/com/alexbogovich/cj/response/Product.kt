package com.alexbogovich.cj.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

data class CjProduct(
    @field:JacksonXmlProperty(localName = "ad-id")
    val adId: String?,

    @field:JacksonXmlProperty(localName = "advertiser-id")
    val advertiserId: String?,

    @field:JacksonXmlProperty(localName = "advertiser-name")
    val advertiserName: String?,

    @field:JacksonXmlProperty(localName = "advertiser-category")
    val advertiserCategory: String?,

    @field:JacksonXmlProperty(localName = "buy-url")
    val buyUrl: String?,

    @field:JacksonXmlProperty(localName = "catalog-id")
    val categoryId: String?,

    val currency: String?,

    val description: String?,

    @field:JacksonXmlProperty(localName = "image-url")
    val imageUrl: String?,

    @field:JacksonXmlProperty(localName = "in-stock")
    val inStock: Boolean?,

    val isbn: String?,

    @field:JacksonXmlProperty(localName = "manufacturer-name")
    val manufacturerName: String?,

    @field:JacksonXmlProperty(localName = "manufacturer-sku")
    val manufacturerSku: String?,

    val name: String?,

    val price: Double?,

    @field:JacksonXmlProperty(localName = "retail-price")
    val retailPrice: Double?,

    @field:JacksonXmlProperty(localName = "sale-price")
    val salePrice: Double?,

    val sku: String?,

    val upc: String?
)

data class CjProducts(
    @field:JacksonXmlProperty(localName = "total-matched", isAttribute = true)
    val total: Int,

    @field:JacksonXmlProperty(localName = "records-returned", isAttribute = true)
    val perPage: Int,

    @field:JacksonXmlProperty(localName = "page-number", isAttribute = true)
    val page: Int,

    @field:JacksonXmlElementWrapper(useWrapping = false)
    val product: List<CjProduct>
)

@JacksonXmlRootElement(localName = "cj-api")
data class CjProductsResponse(
    val products: CjProducts
)
