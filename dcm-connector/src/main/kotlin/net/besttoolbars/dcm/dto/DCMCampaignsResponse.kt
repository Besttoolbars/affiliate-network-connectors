package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMEmptyStringDeserializer
import java.time.Instant

data class DCMCampaignsResponse(
    val status: Number,
    val message: String,
    val campaigns: List<DCMCampaign>?
)

data class DCMCampaign(
    @JsonProperty("campaignIdX")
    val id: Int,

    val name: String,

    val status: String,

    val advertiserId: Int,

    val offerId: Int,

    val offer_name: String,

    @JsonProperty("min_cart_value")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val minCartValue: String?,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val discount: String?,

    val description: String,

    @JsonProperty("start_date")
    val startDate: Instant,

    @JsonProperty("end_date")
    val endDate: Instant?,

    val title: String,

    @JsonProperty("payoutAddionalDetailsList")
    val payoutAdditionalDetailsList: List<PayoutAdditionalDetails>?,

    @JsonProperty("user_discount")
    val userDiscount: Double?,

    @JsonProperty("user_discount_type")
    val userDiscountType: Int // непонятно что значит знаечния 0, 1
)

data class PayoutAdditionalDetails(
    val content: String,

    val type: Int,

    @JsonProperty("type_diplay")
    val typeDisplay: String,

    val value: Double
)