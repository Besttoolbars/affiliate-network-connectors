package net.besttoolbars.dcm.dto

data class DCMOfferWithAttachedList(
    val totalCount: Int,
    val items: List<DCMOfferWithAttached>
)

data class DCMOfferWithAttached(
    val offer: DCMOffer,
    val affiliateOffer: DCMAffiliateOffer,
    val categories: List<DCMCategory>,
    val logo: DCMOfferFile?
)

data class DCMReceiptsList(
    val totalCount: Int,
    val items: List<DCMReceipt>
)