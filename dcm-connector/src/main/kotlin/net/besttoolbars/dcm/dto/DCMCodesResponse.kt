package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class DCMCodesResponse(
    val status: Int,
    val message: String,
    val campaignCodes: List<DCMCampaignCode>?
)

data class DCMCampaignCode(
    val status: Int,
    val campaignCode: String,
    val campaignId: Int,

    @JsonProperty("campaign_name")
    val campaignName: String,
    val advertiserId: Int,
    val offerId: Int,

    @JsonProperty("offer_name")
    val offerName: String,

    @JsonProperty("start_date")
    val startDate: Instant,

    @JsonProperty("end_date")
    val endDate: Instant?,
    val title: String
)