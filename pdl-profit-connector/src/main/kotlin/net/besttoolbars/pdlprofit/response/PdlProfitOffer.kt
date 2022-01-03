package net.besttoolbars.pdlprofit.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.pdlprofit.json.PdlProfitCustomDoubleDeserializer

@JsonIgnoreProperties(ignoreUnknown = true)
data class PdlProfitOffer(
    val id: Int,
    val image: String,
    val name: String,
    val term: String?,
    val url: String,
    @JsonProperty("country_code")
    val countryCode: String,
    val cr: Double?,
    @JsonDeserialize(using = PdlProfitCustomDoubleDeserializer::class)
    val credit: Double,
    @JsonProperty("credit_repeat")
    @JsonDeserialize(using = PdlProfitCustomDoubleDeserializer::class)
    val creditRepeat: Double,
    val days: Int,
    val ecpc: Double?,
    @JsonProperty("first_credit")
    @JsonDeserialize(using = PdlProfitCustomDoubleDeserializer::class)
    val firstCredit: Double,
    @JsonProperty("second_credit")
    @JsonDeserialize(using = PdlProfitCustomDoubleDeserializer::class)
    val secondCredit: Double,
    @JsonProperty("first_credit_percent")
    @JsonDeserialize(using = PdlProfitCustomDoubleDeserializer::class)
    val firstCreditPercent: Double,
    @JsonProperty("first_credit_percent_standard")
    @JsonDeserialize(using = PdlProfitCustomDoubleDeserializer::class)
    val firstCreditPercentStandard: Double
)