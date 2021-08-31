package net.besttoolbars.awin.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import net.besttoolbars.awin.AwinTransactionStatus
import java.time.LocalDateTime

typealias AwinTransactionResponse = List<AwinTransaction>

/**
 * @property lapseTime is the conversion time in seconds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinTransaction(
    val id: Long,
    val url: String,
    val advertiserId: Long,
    val publisherId: Long,
    val commissionSharingPublisherId: Long?,
    val commissionSharingSelectedRatePublisherId: Long?,
    val siteName: String,
    val commissionStatus: AwinTransactionStatus,
    val commissionAmount: Amount,
    val saleAmount: Amount,
    val campaign: String? = null,
    val ipHash: String? = null,
    val customerCountry: String? = null,
    val clickRefs: ClickRefs? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val clickDate: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val transactionDate: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val validationDate: LocalDateTime? = null,
    val type: String? = null,
    val declineReason: String? = null,
    val voucherCodeUsed: Boolean = false,
    val voucherCode: String? = null,
    val lapseTime: Long = 0,
    val amended: Boolean = false,
    val amendReason: String? = null,
    val oldSaleAmount: Any? = null,
    val oldCommissionAmount: Any? = null,
    val clickDevice: String? = null,
    val transactionDevice: String? = null,
    val publisherUrl: String? = null,
    val advertiserCountry: String? = null,
    val orderRef: String? = null,
    val customParameters: List<CustomParameter>? = null,
    val transactionParts: List<Part>? = null,
    val paidToPublisher: Boolean = false,
    val paymentId: Long = 0,
    val transactionQueryId: Long = 0,
    val originalSaleAmount: Any? = null,
    val basketProducts: List<BasketProduct>? = null
) {
    data class Amount(
        val amount: Double,
        val currency: String
    )

    data class CustomParameter(
        val key: String,
        val value: String
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Part(
        val commissionGroupId: Long,
        val amount: Double,
        val commissionAmount: Double,
        val advertiserCost: Double?,
        val commissionGroupCode: String,
        val commissionGroupName: String
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class BasketProduct(
        val quantity: Int,
        val unitPrice: Double,
        val skuCode: String?,
        val category: String,
        val productId: String?,
        val productName: String,
        val commissionGroupCode: String
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class ClickRefs(
        val clickRef: String? = null,
        val clickRef2: String? = null,
        val clickRef3: String? = null,
        val clickRef4: String? = null,
        val clickRef5: String? = null,
        val clickRef6: String? = null
    )
}
