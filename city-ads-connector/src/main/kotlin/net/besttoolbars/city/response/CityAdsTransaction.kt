package net.besttoolbars.city.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsTransactionsResponse (
    val status: Long? = null,
    val error: String? = null,
    val requestID: Long? = null,
    val data: CityAdsTransactionsData = CityAdsTransactionsData()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsTransactionsData (
    val total: Long? = null,
    val timeZoneAPIStat: String? = null,
    val items: List<CityAdsTransactions> = listOf()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsTransactions (
    val commission: Double? = null,
    val commissionApproved: Double? = null,
    val commissionCancelled: Double? = null,
    val commissionOpen: Double? = null,
    val commissionType: String? = null,
    val conversion: String? = null,
    val customerType: String? = null,
    val offerID: String? = null,
    val offerName: String? = null,
    val orderID: String? = null,
    val orderTotal: Double? = null,
    val originalBasket: String? = null,
    val originalCurrency: String? = null,
    val paymentMethod: String? = null,
    val status: Status? = null,
    val submissionID: String? = null,
    val wmCurrency: String? = null,
    val xid: String? = null
){

    enum class Status {
        CANCELLED,
        REJECTED,
        OPEN,
        APPROVED
    }
}