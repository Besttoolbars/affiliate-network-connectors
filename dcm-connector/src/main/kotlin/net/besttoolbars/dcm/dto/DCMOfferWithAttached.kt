package net.besttoolbars.dcm.dto

data class DCMOfferWithAttachedList(
    val totalCount: Int,
    val items: List<DCMOfferWithAttached>
)

data class DCMOfferWithAttached(
    val offer: DCMOffer,
    val offerUrls: List<DCMOfferUrl>,
    val offerFiles: List<DCMOfferFile>,
    val categories: List<DCMCategory>
)