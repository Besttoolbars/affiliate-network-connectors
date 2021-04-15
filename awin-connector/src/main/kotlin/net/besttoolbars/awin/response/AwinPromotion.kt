package net.besttoolbars.awin.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.awin.AwinPromotionType
import net.besttoolbars.awin.json.AwinCsvBooleanDeserializer
import net.besttoolbars.awin.json.AwinInstantDeserializer
import net.besttoolbars.awin.json.AwinCsvStringListDeserializer
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinPromotion(
    @JsonProperty("Promotion ID")
    val promotionId: String,
    @JsonProperty("Advertiser")
    val advertiser: String,
    @JsonProperty("Advertiser ID")
    val advertiserId: Long,
    @JsonProperty("Type")
    val type: AwinPromotionType,
    @JsonProperty("Code")
    val code: String?,
    @JsonProperty("Description")
    val description: String?,
    @JsonProperty("Starts")
    @JsonDeserialize(using = AwinInstantDeserializer::class)
    val starts: Instant,
    @JsonProperty("Ends")
    @JsonDeserialize(using = AwinInstantDeserializer::class)
    val ends: Instant,
    @JsonProperty("Categories")
    @JsonDeserialize(using = AwinCsvStringListDeserializer::class)
    val categories: List<String>,
    @JsonProperty("Regions")
    @JsonDeserialize(using = AwinCsvStringListDeserializer::class)
    val regions: List<String>,
    @JsonProperty("Terms")
    val terms: String?,
    @JsonProperty("Deeplink Tracking")
    val deeplinkTracking: String,
    @JsonProperty("Deeplink")
    val deeplink: String,
    @JsonProperty("Commission Groups")
    @JsonDeserialize(using = AwinCsvStringListDeserializer::class)
    val commissionGroups: List<String>,
    @JsonProperty("Commission")
    val commission: String?,
    @JsonProperty("Exclusive")
    @JsonDeserialize(using = AwinCsvBooleanDeserializer::class)
    val exclusive: Boolean,
    @JsonProperty("Date Added")
    @JsonDeserialize(using = AwinInstantDeserializer::class)
    val dateAdded: Instant,
    @JsonProperty("Title")
    val title: String,
    @JsonProperty("Campaign")
    val campaign: String?
)