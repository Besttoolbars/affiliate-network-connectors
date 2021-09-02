package net.besttoolbars.pdlprofit.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.pdlprofit.json.PdlProfitCreditPercentDeserializer

@JsonIgnoreProperties(ignoreUnknown = true)
data class PdlProfitOffer(
    val id: Int,
    val image: String,
    val name: String,
    val term: Int,
    val url: String,
    @JsonProperty("country_code")
    val countryCode: String,
    val cr: Double?,
    val credit: Int,
    @JsonProperty("credit_repeat")
    val creditRepeat: Int,
    val days: Int,
    val ecpc: Double?,
    @JsonProperty("first_credit")
    val firstCredit: Int,
    @JsonProperty("second_credit")
    val secondCredit: Int,
    @JsonProperty("first_credit_percent")
    @JsonDeserialize(using = PdlProfitCreditPercentDeserializer::class)
    val firstCreditPercent: Double,
    @JsonProperty("first_credit_percent_standard")
    @JsonDeserialize(using = PdlProfitCreditPercentDeserializer::class)
    val firstCreditPercentStandard: Double
)