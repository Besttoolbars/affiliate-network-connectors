package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMEmptyStringDeserializer
import net.besttoolbars.dcm.deserializers.DCMNullableNumberDeserializer
import java.time.LocalDateTime

data class DCMConversionReport(
    val id: Int,

    @JsonProperty("conversion_status")
    val status: ConversationStatus,

    val currency: String,

    val datetime: LocalDateTime,

    @JsonProperty("offer_id")
    val offerId: Int,

    @JsonProperty("offer_url_id")
    @JsonDeserialize(using = DCMNullableNumberDeserializer::class)
    val offerUrlId: Int?,

    val payout: Double,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    @JsonProperty("affiliate_info1")
    val coupon: String?,

    @JsonProperty("payout@AED")
    @JsonDeserialize(using = DCMNullableNumberDeserializer::class)
    val payoutInAED: Double?,

    @JsonProperty("payout@USD")
    @JsonDeserialize(using = DCMNullableNumberDeserializer::class)
    val payoutInUSD: Double?
)

data class DCMConversionsMobile(
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    @JsonProperty("affiliate_click_id")
    val clickId: String?
)

enum class ConversationStatus {
    @JsonProperty("approved")
    APPROVED,

    @JsonProperty("pending")
    PENDING,

    @JsonProperty("rejected")
    REJECTED
}