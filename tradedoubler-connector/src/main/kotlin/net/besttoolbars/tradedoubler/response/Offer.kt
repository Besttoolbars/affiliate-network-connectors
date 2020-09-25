package net.besttoolbars.tradedoubler.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class TradedoublerOffer (
    val id: Long,
    val programId: Long,
    val programName: String,
    val code: String? = null,
    val title: String? = null,
    val shortDescription: String? = null,
    val description: String? = null,
    val voucherTypeId: Int? = null,
    val defaultTrackUri: String,
    val siteSpecific: Boolean,
    val landingUrl: String,
    val discountAmount: Double? = null,
    val isPercentage: Boolean,
    val exclusive: Boolean,
    val updateDate: Instant,
    val publishStartDate: Instant,
    val publishEndDate: Instant,
    val startDate: Instant,
    val endDate: Instant
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TradedoublerOfferResponse(
    val offers: List<TradedoublerOffer> = emptyList()
)