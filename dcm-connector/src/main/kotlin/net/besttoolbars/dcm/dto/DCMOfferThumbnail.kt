package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DCMOfferThumbnail(
    @JsonProperty("offer_id")
    val offerId: Int,

    @JsonProperty("Thumbnail")
    val thumbnail: Map<Int, DCMOfferFile>
)