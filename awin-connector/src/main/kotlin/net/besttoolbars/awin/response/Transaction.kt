package net.besttoolbars.awin.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import net.besttoolbars.awin.TransactionStatus
import java.time.LocalDateTime

typealias AwinTransactionResponse = List<AwinTransaction>

/**
 * @property lapseTime is the conversion time in seconds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinTransaction (
    val id: Long,
    val url: String,
    val advertiserId: Long,
    val publisherId: Long,
    val commissionSharingPublisherId: Long,
    val commissionSharingSelectedRatePublisherId: Long,
    val siteName: String,
    val commissionStatus: TransactionStatus,
    val commissionAmount: Amount,
    val saleAmount: Amount,
    val ipHash: String? = null,
    val customerCountry: String? = null,
    val clickRefs: Map<String, String>? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val clickDate: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val transactionDate: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val validationDate: LocalDateTime? = null,
    val type: String? = null,

    val declineReason: Any? = null,

    val voucherCodeUsed: Boolean = false,
    val voucherCode: String? = null,
    val lapseTime: Long = 0,
    val amended: Boolean = false,

    val amendReason: Any? = null,
    val oldSaleAmount: Any? = null,
    val oldCommissionAmount: Any? = null,

    val clickDevice: String? = null,
    val transactionDevice: String? = null,
    val publisherUrl: String? = null,
    val advertiserCountry: String? = null,
    val orderRef: String? = null,
    val customParameters: List<CustomParameter>? = null,
    val transactionParts: List<TransactionPart>? = null,
    val paidToPublisher: Boolean = false,
    val paymentId: Long = 0,
    val transactionQueryId: Long = 0,

    val originalSaleAmount: Any? = null
)

data class Amount (
    val amount: Double,
    val currency: String
)

data class CustomParameter (
    val key: String,
    val value: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TransactionPart (
    val commissionGroupId: Long,
    val amount: Double,
    val commissionAmount: Double,
    val advertiserCost: Double?,
    val commissionGroupCode: String,
    val commissionGroupName: String
)
