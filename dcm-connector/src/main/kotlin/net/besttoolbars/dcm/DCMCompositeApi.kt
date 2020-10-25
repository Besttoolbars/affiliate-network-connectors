package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.*

class DCMCompositeApi(
    private val api: DCMApi
) {
    fun getOffersWithAttachedData(
        apiKey: String,
        page: Int,
        limit: Int
    ): DCMOfferWithAttachedList {
        val offersResponse: DCMResponse<DCMOfferLimitedList> = api.getApprovedOffers(apiKey, page, limit).get().response
        val offersList: DCMOfferLimitedList = getOrThrow(offersResponse, "getApprovedOffers")
        val offers: Collection<DCMOfferListData> = offersList.data.values
        val offerIds = offers.map { it.offer.id }

        val categories: List<DCMOfferCategory> = getCategories(apiKey, offerIds)
        val logos: List<DCMOfferThumbnail> = getThumbnails(apiKey, offerIds)

        val items: List<DCMOfferWithAttached> = offers.map {
            val offer: DCMOffer = it.offer
            val offerCategories: List<DCMCategory> = categories.find { it.offerId == offer.id }?.categories?.values?.toList() ?: emptyList()
            val logo: DCMOfferFile? = logos.find { it.offerId == offer.id }?.thumbnail?.values?.firstOrNull()

            DCMOfferWithAttached(offer, offerCategories, logo)
        }

        return DCMOfferWithAttachedList(
            items = items,
            totalCount = offersList.count
        )
    }

    fun getCategories(
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

    fun getOfferUrls(
        apiKey: String,
        offerId: Int? = null
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

    fun getAllReceipts(
        apiKey: String,
        page: Int,
        limit: Int
    ): DCMReceiptsList {
        val receiptsResponse: DCMResponse<DCMReceiptsLimitedList> = api.getAllReceipts(
            apiKey = apiKey,
            page = page,
            limit = limit
        ).get().response
        val receiptsList = getOrThrow(receiptsResponse, "getAllReceipts")
        return DCMReceiptsList(
            totalCount = receiptsList.count,
            items = receiptsList.data.map { it.value.receipt }
        )
    }

    private fun <T> getOrThrow(response: DCMResponse<T>, method: String): T {
        if (response.errors.isNotEmpty() || response.data == null) {
            throw DCMException(method = method, errors = response.errors)
        }
        return response.data
    }
}