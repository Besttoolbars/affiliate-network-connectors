package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.*
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DCMCompositeApiImpl(
    private val api: DCMApi,
    private val partnerApi: PartnerDCMApi
) : DCMCompositeApi {
    override fun getOffersWithAttachedData(apiKey: String): DCMOfferWithAttachedList {
        val offersResponse: DCMResponse<Map<String, DCMOfferListData>> = api.getApprovedOffers(apiKey).get().response
        val offersList: Map<String, DCMOfferListData> = getOrThrow(offersResponse, "getApprovedOffers")
        val offers: Collection<DCMOfferListData> = offersList.values
        val offerIds = offers.map { it.offer.id }

        val categories: List<DCMOfferCategory> = getCategories(apiKey, offerIds)
        val logos: List<DCMOfferThumbnail> = getThumbnails(apiKey, offerIds)

        val items: List<DCMOfferWithAttached> = offers.map {
            val offer: DCMOffer = it.offer
            val affiliateOffer: DCMAffiliateOffer? = it.affiliateOffer
            val offerCategories: List<DCMCategory> = categories.find { it.offerId == offer.id }?.categories?.values?.toList() ?: emptyList()
            val logo: DCMOfferFile? = logos.find { it.offerId == offer.id }?.thumbnail?.values?.firstOrNull()

            DCMOfferWithAttached(offer, affiliateOffer, offerCategories, logo)
        }

        return DCMOfferWithAttachedList(
            items = items,
            totalCount = offersList.size
        )
    }

    override fun getCategories(
        apiKey: String,
        offerIds: List<Int>
    ): List<DCMOfferCategory> {
        val response: DCMResponse<List<DCMOfferCategory>> = api.getCategories(apiKey, offerIds).get().response
        return getOrThrow(response, "getCategories")
    }

    fun getThumbnails(
        apiKey: String,
        offerIds: List<Int>
    ): List<DCMOfferThumbnail> {
        val response: DCMResponse<List<DCMOfferThumbnail>> = api.getThumbnails(apiKey, offerIds).get().response
        return getOrThrow(response, "getThumbnails")
    }

    override fun getOfferUrls(
        apiKey: String,
        offerId: Int?
    ): List<DCMOfferUrl> {
        var urls = listOf<DCMOfferUrl>()
        var page = 1

        while (true) {
            val response: DCMResponse<DCMOfferUrlLimitedList> = api.getOffersUrls(
                apiKey = apiKey,
                page = page,
                limit = 100,
                offerId = offerId
            ).get().response
            val data: DCMOfferUrlLimitedList = getOrThrow(response, "getOffersUrls")

            urls = urls + data.data.values.toList().map { it.offerUrl }
            if (urls.size >= data.count) {
                break
            }
            page++
        }

        return urls
    }

    override fun getAllReceipts(
        apiKey: String,
        page: Int,
        limit: Int,
        date: LocalDateTime
    ): DCMReceiptsList {
        val receiptsResponse: DCMResponse<DCMReceiptsLimitedList> = api.getAllReceipts(
            apiKey = apiKey,
            page = page,
            limit = limit,
            filterByDate = date.format(formatter)
        ).get().response
        val receiptsList = getOrThrow(receiptsResponse, "getAllReceipts")
        return DCMReceiptsList(
            totalCount = receiptsList.count,
            items = receiptsList.data.map { it.value.receipt }
        )
    }

    override fun generateTrackingLink(
        apiKey: String,
        offerId: Int,
        transactionId: String?,
        offerUrlId: Int?
    ): URI {
        val linkResponse = api.generateTrackingLink(
            apiKey = apiKey,
            offerId = offerId,
            transactionId = transactionId,
            offerUrlId = offerUrlId
        ).get().response
        val link = getOrThrow(linkResponse, "generateTrackingLink")
        return link.clickUrl
    }

    override fun getCampaigns(apiKey: String): List<DCMCampaign> {
        val campaignsResponse = partnerApi.getCampaigns(apiKey).get()

        if (campaignsResponse.status != 200 || campaignsResponse.campaigns == null) {
            throw DCMException(method = "getCampaigns", errors = listOf(DCMError(campaignsResponse.message)))
        }

        return campaignsResponse.campaigns
    }

    override fun getCodes(apiKey: String, campaignId: Int?): List<DCMCampaignCode> {
        val codesResponse = partnerApi.getCodes(apiKey).get()

        if (codesResponse.status != 200 || codesResponse.campaignCodes == null) {
            throw DCMException(method = "getCodes", errors = listOf(DCMError(codesResponse.message)))
        }

        return codesResponse.campaignCodes
    }

    override fun getConversationReports(apiKey: String, page: Int, limit: Int, date: LocalDate): List<DCMConversionReportData> {
        val dateStr = date.format(formatter)
        val response = api.getConversationReports(
            apiKey,
            page,
            limit,
            dateStr
        ).get().response
        val reports: DCMConversionReportListData = getOrThrow(response, "getConversationReports")
        return reports.data
    }

    private fun <T> getOrThrow(response: DCMResponse<T>, method: String): T {
        if (response.errors.isNotEmpty() || response.data == null) {
            throw DCMException(method = method, errors = response.errors)
        }
        return response.data
    }

    private companion object {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}