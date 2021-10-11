package net.besttoolbars.pdlprofit.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class PdlProfitConversion(
    val city: String?,
    @JsonProperty("click_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    val clickDate: Instant,
    @JsonProperty("click_unix_date")
    val clickUnixDate: Int,
    @JsonProperty("country_code")
    val countryCode: String?,
    val currency: String?,
    val gclid: String?,
    val ip: String?,
    @JsonProperty("lead_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    val leadDate: Instant?,
    @JsonProperty("lead_type")
    val leadType: String,
    val name: String,
    @JsonProperty("postback_unix_date")
    val postbackUnixDate: Int?,
    @JsonProperty("s_date")
    val sDate: LocalDate?,
    @JsonProperty("sub_id")
    val subId: String?,
    @JsonProperty("subid2")
    val subId2: String?,
    @JsonProperty("subid3")
    val subId3: String?,
    @JsonProperty("summ")
    val summ: Double?,
    @JsonProperty("transaction_id")
    val transactionId: String?,
    @JsonProperty("user_detailed_data")
    val userDetailedData: String?,
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
    val utmTerm: String?
)