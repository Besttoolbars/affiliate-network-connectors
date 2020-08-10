package net.besttoolbars.lomadee.response

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
    val installment: Map<Any, Any>,
    val store: OfferStore
)

data class OfferCategory(
    val id: Int,
    val name: String,
    val hasOffer: Int
)

data class OfferStore (
    val id: Long,
    val name: String,
    val thumbnail: String,
    val link: String,
    val hasOffer: Long,
    val maxCommission: Long,
    val events: List<Event>
)

data class Event (
    val event: String,
    val eventType: String,
    val fixedCommission: Boolean,
    val commission: Long
)