package net.besttoolbars.lomadee.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class OffersResponse (
    val requestInfo: RequestInfo,
    val pagination: Pagination,
    val offers: List<Offer>
)

data class OfferCategoriesResponse (
    val requestInfo: RequestInfo,
    val pagination: Pagination,
    val categories: List<OfferCategory>
)

data class OfferStoresResponse (
    val requestInfo: RequestInfo,
    val pagination: Pagination,
    val stores: List<OfferStore>
)

data class Offer (
    val id: String,
    val name: String,
    val category: OfferCategory,
    val link: String,
    val thumbnail: String,
    val price: Long,
    val installment: OfferInstallment,
    val store: OfferStore
)

data class OfferCategory(
    val id: Int,
    val name: String,
    val hasOffer: Int = 0,
    val link: String? = null
)

data class OfferStore(
    val id: Long,
    val name: String,
    val thumbnail: String,
    val link: String,
    val hasOffer: Long? = null,
    val maxCommission: Double? = null,
    val events: List<OfferEvent> = emptyList()
)

data class OfferEvent(
    val event: String,
    val eventType: String,
    val fixedCommission: Boolean,
    val commission: Double
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class OfferInstallment(
    val quantity: Int = 0,
    val value: Double = 0.0
)