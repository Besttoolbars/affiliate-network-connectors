package net.besttoolbars.cj.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.cj.json.CjInstantDeserializer
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShoppingProduct(
    val additionalImageLink: List<String>?,

    val adult: String?,

    val adId: String?,

    val advertiserId: String?,

    val advertiserName: String?,

    val advertiserCountry: String?,

    val ageGroup: String?,

    val availability: String?,

    @JsonDeserialize(using = CjInstantDeserializer::class)
    val availabilityDate: Instant?,

    val brand: String?,

    val catalogId: String?,

    val color: String?,

    val condition: String?,

    val customLabel0: String?,

    val customLabel1: String?,

    val customLabel2: String?,

    val customLabel3: String?,

    val customLabel4: String?,

    val description: String?,

    val energyEfficiencyClass: String?,

    val energyEfficiencyClassMax: String?,

    val energyEfficiencyClassMin: String?,

    @JsonDeserialize(using = CjInstantDeserializer::class)
    val expirationDate: Instant?,

    val gender: String?,

    val googleProductCategory: GoogleProductCategory?,

    val gtin: String?,

    val id: String?,

    val identifierExists: String?,

    val imageLink: String?,

    val installment: Installment?,

    val isBundle: String?,

    val itemGroupId: String?,

    val itemListId: String?,

    val itemListName: String?,

    @JsonDeserialize(using = CjInstantDeserializer::class)
    val lastUpdated: Instant?,

    val link: String?,

    val linkCode: LinkCode?,

    val loyaltyPoints: LoyaltyPoints?,

    val material: String?,

    val maximumHandlingTime: String?,

    val minimumHandlingTime: String?,

    val mobileLink: String?,

    val mpn: String?,

    val multipack: String?,

    val pattern: String?,

    val price: AmountWithCurrency?,

    val productType: List<String>?,

    val salePrice: AmountWithCurrency?,

    @JsonDeserialize(using = CjInstantDeserializer::class)
    val salePriceEffectiveDateEnd: Instant?,

    @JsonDeserialize(using = CjInstantDeserializer::class)
    val salePriceEffectiveDateStart: Instant?,

    val serviceableAreas: String?,

    val shipping: Shipping?,

    val size: String?,

    val sizeSystem: String?,

    val sizeType: String?,

    val sourceFeedType: String?,

    val targetCountry: String?,

    val tax: Tax?,

    val title: String?,

    val unitPricingBaseMeasure: String?,

    val unitPricingMeasure: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShoppingProducts(
    val count: Int?,
    val limit: Int?,
    val totalCount: Int?,
    val resultList: List<ShoppingProduct>?
)

data class CJShoppingProductsResponse(
    val shoppingProducts: ShoppingProducts
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleProductCategory(
    val id: Int?,
    val name: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Installment(
    val months: Int?,
    val amount: AmountWithCurrency?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AmountWithCurrency(
    val amount: Double?,
    val currency: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LinkCode(
    val clickUrl: String?,
    val imageUrl: String?,
    val html: LinkType?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LinkType(
    val form: String?,
    val image: String?,
    val text: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoyaltyPoints(
    val name: String?,
    val points: Int?,
    val ratio: Double?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Shipping(
    val country: String?,
    val height: String?,
    val length: String?,
    val locationGroupName: String?,
    val locationId: String?,
    val postalCode: String?,
    val price: AmountWithCurrency?,
    val region: String?,
    val service: String?,
    val weight: String?,
    val width: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tax(
    val countryCode: String?,
    val locationGroupName: String?,
    val locationId: String?,
    val postalCode: String?,
    val rate: Double?,
    val region: String?,
    val taxShip: String?
)