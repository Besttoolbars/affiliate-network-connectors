package net.besttoolbars.tradedoubler.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class TradedoublerProductResponse (
    val productHeader: ProductHeader? = null,
    val products: List<Product> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductHeader (
    val totalHits: Long? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Product (
    val name: String,
    val productImage: ProductImage,
    val language: String? = null,
    val description: String,
    val identifiers: Identifiers? = null,
    val fields: List<ProductField> = emptyList(),
    val offers: List<ProductOffer> = emptyList(),
    val categories: List<ProductCategory> = emptyList()
)

data class ProductField (
    val name: String,
    val value: String
)

data class Identifiers (
    val sku: String
)

data class ProductCategory(
    val name: String,
    val id: Long? = null,
    val tdCategoryName: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductOffer (
    val feedId: Long,
    val productURL: String,
    val priceHistory: List<PriceHistory> = emptyList(),
    val modified: Instant,
    val availability: String? = null,
    val deliveryTime: String? = null,
    val condition: String? = null,
    val shippingCost: String? = null,
    val sourceProductId: String,
    val programName: String,
    val id: String,
    val sourceProductURL: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PriceHistory (
    val price: Price,
    val date: Instant
)

data class Price (
    val value: String,
    val currency: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductImage (
    val url: String
)