package net.besttoolbars.dcm.response

import com.fasterxml.jackson.annotation.JsonProperty

data class DCMOfferList(
//    @JsonDeserialize(using = DCMIntegerDeserializer::class)
    val page: Int,
    val current: Int,
    val count: Int,
    val pageCount: Int,
    val data: Map<String, DCMOfferListData>
)

data class DCMOfferListData(
    @JsonProperty("Offer")
    val offer: DCMOffer
)