package net.besttoolbars.awin.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import net.besttoolbars.awin.CountryCode
import net.besttoolbars.awin.TransactionStatus
import java.time.LocalDateTime

/**
 * @property lapseTime is the conversion time in seconds
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinTransactionResponse(
    val transactions: List<AwinTransaction> = emptyList()
)

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
    val customerCountry: CountryCode,
    val clickRefs: List<Map<String, String>> = emptyList(),
    val clickDate: LocalDateTime,
    val transactionDate: LocalDateTime,
    val validationDate: Int? = null,
    val type: String,

    val declineReason: Any? = null,

    val voucherCodeUsed: Boolean,
    val voucherCode: String,
    val lapseTime: Long,
    val amended: Boolean,

    val amendReason: Any? = null,
    val oldSaleAmount: Any? = null,
    val oldCommissionAmount: Any? = null,

    val clickDevice: String,
    val transactionDevice: String,
    val publisherUrl: String,
    val advertiserCountry: CountryCode,
    val orderRef: String,
    val customParameters: List<CustomParameter> = emptyList(),
    val transactionParts: List<TransactionPart> = emptyList(),
    val paidToPublisher: Boolean,
    val paymentId: Long,
    val transactionQueryId: Long,

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

data class TransactionPart (
    val commissionGroupId: Long,
    val amount: Double,
    val commissionAmount: Double,
    val commissionGroupCode: String,
    val commissionGroupName: String
)
