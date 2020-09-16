package net.besttoolbars.dcm.response

import com.fasterxml.jackson.annotation.JsonProperty

data class DCMOfferCategory(
    @JsonProperty("offer_id")
    val offerId: Int,
    val categories: Map<Int, DCMCategory>
)

data class DCMCategory(
    val id: Int,
    val name: String
)