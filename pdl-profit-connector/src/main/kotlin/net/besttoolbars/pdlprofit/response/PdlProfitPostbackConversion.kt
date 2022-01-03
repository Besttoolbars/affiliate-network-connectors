package net.besttoolbars.pdlprofit.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class PdlProfitPostbackConversion(
    @JsonProperty("offer_name")
    val offerName: String,
    @JsonProperty("offer_id")
    val offerId: Long,
    @JsonProperty("lead_id")
    val leadId: Long,
    @JsonProperty("lead_status")
    val leadStatus: Status,
    @JsonProperty("lead_date")
    @JsonFormat(pattern = "HH-mm-ss-dd-MM-yyyy", timezone = "UTC")
    val leadDate: Instant,
    @JsonProperty("click_date")
    @JsonFormat(pattern = "HH-mm-ss-dd-MM-yyyy", timezone = "UTC")
    val clickDate: Instant,
    @JsonProperty("transaction_id")
    val transactionId: Long?,
    @JsonProperty("aff_rev")
    val affRev: Double,
    @JsonProperty("aff_rev_real_cur")
    val affRevRealCur: Double,
    @JsonProperty("real_cur")
    val realCur: String,
    val currency: String,
    @JsonProperty("lead_type")
    val leadType: String,
    @JsonProperty("click_ip")
    val clickIp: String?,
    @JsonProperty("summ")
    val summ: Double?,
    @JsonProperty("sub_id")
    val subId: String?,
    @JsonProperty("subid2")
    val subId2: String?,
    @JsonProperty("subid3")
    val subId3: String?,
    @JsonProperty("utm_adgroup")
    val utmAdgroup: String?,
    @JsonProperty("utm_adposition")
    val utmAdposition: String?,
    @JsonProperty("utm_campaign")
    val utmCampaign: String?,
    @JsonProperty("utm_creative")
    val utmCreative: String?,
    @JsonProperty("utm_device")
    val utmDevice: String?,
    @JsonProperty("utm_medium")
    val utmMedium: String?,
    @JsonProperty("utm_source")
    val utmSource: String?,
    @JsonProperty("utm_term")
    val utmTerm: String?,
    val gclid: String?
) {
    enum class Status {
        PENDING,
        REJECTED,
        APPROVED
    }
}