package net.besttoolbars.dcm.response

import com.fasterxml.jackson.annotation.JsonProperty

data class DCMLimitedList<T>(
    val page: Int,
    val current: Int,
    val count: Int,
    val pageCount: Int,
    val data: Map<String, T>
)

data class DCMOfferListData(
    @JsonProperty("Offer")
    val offer: DCMOffer
)

data class DCMOfferUrlListData(
    @JsonProperty("OfferUrl")
    val offerUrl: DCMOfferUrl
)

data class DCMOfferFileListData(
    @JsonProperty("OfferFile")
    val offerFile: DCMOfferFile
)