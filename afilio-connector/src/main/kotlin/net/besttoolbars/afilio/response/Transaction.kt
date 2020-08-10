package net.besttoolbars.afilio.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class TransactionResponse(
    val root: Any,
    val list: List<Any>,
    val nextUrl: String,
    val lead: Lead? = null,
    val sale: Sale? = null
)

data class Lead(
    val lead: String,
    @JsonProperty("leadid")
    val leadId: Int,
    @JsonProperty("affid")
    val affId: Int,
    val status: TransactionStatus,
    @JsonProperty("progid")
    val progId: Int,
    @JsonProperty("order_id")
    val orderId: Int,
    @JsonProperty("order_price")
    val orderPrice: Double,
    val commission: Double,
    val date: Instant,
    val payment: PaymentStatus,
    val xtra: String
)

data class Sale(
    val sale: String,
    @JsonProperty("saleid")
    val saleId: Int,
    @JsonProperty("affid")
    val affId: Int,
    val status: TransactionStatus,
    @JsonProperty("progid")
    val progId: Int,
    @JsonProperty("order_id")
    val orderId: Int,
    @JsonProperty("order_price")
    val orderPrice: Double,
    val commission: Double,
    val date: Instant,
    val payment: PaymentStatus,
    val xtra: String
)

enum class TransactionStatus {
    Pendente,//pending
    Aceito,//accept
    Recusado//cancel
}

enum class PaymentStatus { Paid, Unpaid }
enum class TransactionType { Sale, Lead }
enum class DateType { Transaction, Validation }
enum class GetTransactionMode { list }