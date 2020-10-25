package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMEmptyStringDeserializer

data class DCMReceipt(
    @JsonProperty("affiliate_id")
    val affiliateId: Int,

    val amount: Float,

    val currency: String,

    val date: String,

    val datetime: String,

    val id: Int,

    val memo: String,

    val method: ReceiptMethod,

    @JsonProperty("payment_details")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val paymentDetails: String? = null,

    @JsonProperty("payment_info")
    val paymentInfo: Any? = null,

    val status: ReceiptStatus,

    val token: String,

    @JsonProperty("transaction_id")
    val transactionId: String,

    @JsonProperty("user_id")
    val userId: Int
)

enum class ReceiptStatus {
    SUCCESS
}

enum class ReceiptMethod {
    WIRE,
    CHECK,
    PAYPAL,
    OTHER,
    DIRECT_DEPOSIT,
    PAYONEER,
    PAYQUICKER
}