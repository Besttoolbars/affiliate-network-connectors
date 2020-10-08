package net.besttoolbars.cj.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.cj.json.CjInstantDeserializer
import java.time.Instant

/**
 *
 * @property id Commission identification number.
 * @property offerId Offer/Ad identification number.
 * @property actionStatus Status of the commission.
 * @property validationStatus The commission's validation status. Indicates whether an order is pending validation or has been validated by advertiser.
 * @property advertiserId CID of the advertiser for this commission.
 * @property advertiserName Name of the advertiser for this commission.
 * @property url Click referring URL for the commission.
 * @property coupon The coupon/voucher code used in the transaction.
 * @property discount Discount associated with the order in USD.
 * @property amount Sale amount in USD.
 * @property commission Publisher commission amount in USD.
 * @property orderId Advertiser-assigned identification number for the order.
 * @property date Date on which the commission is posted.
 * @property shopperId Shopper ID for the transaction (Publisher-only attribute).
 * @property source Source for the transaction.
 * @property items The items associated with this commissionable action.
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class CjTransaction (
    @JsonProperty("commissionId")
    val id: String,
    @JsonProperty("aid")
    val offerId: String?,
    val actionStatus: CjCommissionActionStatus,
    val validationStatus: CjCommissionValidationStatus,
    val advertiserId: String,
    val advertiserName: String,
    @JsonProperty("clickReferringURL")
    val url: String?,
    val coupon: String?,
    @JsonProperty("orderDiscountUsd")
    val discount: Double,
    @JsonProperty("saleAmountUsd")
    val amount: Double,
    @JsonProperty("pubCommissionAmountUsd")
    val commission: Double?,
    val orderId: String?,
    @JsonProperty("postingDate")
    @JsonDeserialize(using = CjInstantDeserializer::class)
    val date: Instant?,
    val shopperId: String?,
    val source: String?,
    val items: List<CjTransactionItem>? = listOf(),
    val actionTrackerId: String?,
    @JsonDeserialize(using = CjInstantDeserializer::class)
    val eventDate: Instant,
    @JsonDeserialize(using = CjInstantDeserializer::class)
    val clickDate: Instant?
)

/**
 *
 * @property id Unique id of the item list which applies to this item record.
 * @property commissionItemId The unique id of this item record..
 * @property quantity Item quantity.
 * @property discount Item discount in USD.
 * @property amountPerItem Per item sale amount in USD.
 * @property amount Total commission for the specified quantity of the item in USD.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class CjTransactionItem (
    @JsonProperty("itemListId")
    val id: String?,
    val commissionItemId: String,
    val quantity: Int?,
    @JsonProperty("discountUsd")
    val discount: Double,
    @JsonProperty("perItemSaleAmountUsd")
    val amountPerItem: Double?,
    @JsonProperty("totalCommissionUsd")
    val amount: Double?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjPublisherCommissionResponse (
    val publisherCommissions: CjPublisherCommission
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjPublisherCommission (
    val count: Int,
    val payloadComplete: Boolean,
    val maxCommissionId: String?,
    val records: List<CjTransaction>
)

enum class CjCommissionValidationStatus  {
    PENDING,
    ACCEPTED,
    DECLINED,
    AUTOMATED;
}

enum class CjCommissionActionStatus  {
    NEW,
    LOCKED,
    EXTENDED,
    CLOSED;
}