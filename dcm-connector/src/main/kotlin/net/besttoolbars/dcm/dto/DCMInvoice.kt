package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMBooleanDeserializer

data class DCMInvoice(
    @JsonProperty("affiliate_id")
    val affiliateId: Int,

    val amount: Float,

    val currency: String,

    val datetime: String,

    @JsonProperty("end_date")
    val endDate: String,

    val id: Int,

    @JsonProperty("is_paid")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val isPaid: Boolean,

    val memo: String,

    @JsonProperty("receipt_id")
    val receiptId: Int? = null,

    @JsonProperty("start_date")
    val startDate: String,

    val status: InvoiceStatus
)

enum class InvoiceStatus {
    ACTIVE
}