package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URI

data class DCMTrackingLink(
    @JsonProperty("affiliate_id")
    val affiliateId: Int,

    @JsonProperty("offer_id")
    val offerId: Int,

    @JsonProperty("click_url")
    val clickUrl: URI,

    @JsonProperty("impression_pixel")
    val impressionPixel: String
)