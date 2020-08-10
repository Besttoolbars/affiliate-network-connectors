package net.besttoolbars.afilio.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Coupon(
    @JsonProperty("id_couponoffer")
    val id: String,
    val url: String,
    val code: String,
    val title: String,
    val discount: Double,
    val description: String,
    @JsonProperty("progid")
    val progId: String,
    val rule: String,
    val type: String, //Todo: to enum
    @JsonProperty("startdate")
    val startDate: String,
    @JsonProperty("enddate")
    val endDate: String
)

enum class Format {
    JSON,
    XML,
    CSV
}

enum class GetCouponsMode { dl }