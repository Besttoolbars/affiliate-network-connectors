package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class DCMConversionReport(
    @JsonProperty("Stat.id")
    val id: Int,

    @JsonProperty("Stat.ad_id")
    val transactionId: Int,

    @JsonProperty("ConversionsMobile.affiliate_click_id")
    val clickId: String,

    @JsonProperty("Stat.approved_payout")
    val payout: Double,

    @JsonProperty("Stat.conversion_status")
    val status: ConversationStatus,

    @JsonProperty("Stat.currency")
    val currency: String,

    @JsonProperty("Stat.datetime")
    val date: LocalDateTime,

    @JsonProperty("Stat.offer_id")
    val offerId: Int,

    @JsonProperty("Stat.offer_url_id")
    val offerUrlId: Int?
)

enum class ConversationStatus {
    @JsonProperty("approved")
    APPROVED,

    @JsonProperty("pending")
    PENDING,

    @JsonProperty("rejected")
    REJECTED
}