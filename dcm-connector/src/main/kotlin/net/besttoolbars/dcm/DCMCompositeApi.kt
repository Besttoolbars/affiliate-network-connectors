package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.*
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime

interface DCMCompositeApi {
    fun getOffersWithAttachedData(apiKey: String): DCMOfferWithAttachedList

    fun getCategories(
        apiKey: String,
        offerIds: List<Int>
    ): List<DCMOfferCategory>

    fun getOfferUrls(
        apiKey: String,
        offerId: Int? = null
    ): List<DCMOfferUrl>

    fun getAllReceipts(
        apiKey: String,
        page: Int,
        limit: Int,
        date: LocalDateTime
    ): DCMReceiptsList

    fun generateTrackingLink(
        apiKey: String,
        offerId: Int,
        transactionId: String?,
        offerUrlId: Int? = null
    ): URI

    fun getCampaigns(apiKey: String): List<DCMCampaign>

    fun getCodes(apiKey: String, campaignId: Int? = null): List<DCMCampaignCode>

    fun getConversationReports(
        apiKey: String,
        page: Int,
        limit: Int,
        date: LocalDate
    ): List<DCMConversionReportData>
}