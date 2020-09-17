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

        val categories: List<DCMOfferCategory> = getCategories(apiKey, offers.map { it.offer.id })

        val items: List<DCMOfferWithAttached> = offers.map {
            val offer: DCMOffer = it.offer
            val offerCategories: List<DCMCategory> = categories.find { it.offerId == offer.id }?.categories?.values?.toList() ?: emptyList()
            val offerUrls: List<DCMOfferUrl> =  getOfferUrls(apiKey, offer.id)
            val offerFiles: List<DCMOfferFile> = getOfferFiles(apiKey, offer.id)

            DCMOfferWithAttached(offer, offerUrls, offerFiles, offerCategories)
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

    fun getOfferUrls(
        apiKey: String,
        offerId: Int
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

    fun getOfferFiles(
        apiKey: String,
        offerId: Int
    ): List<DCMOfferFile> {
        var files = listOf<DCMOfferFile>()
        var page = 1

        while (true) {
            val response: DCMResponse<DCMOfferFileLimitedList> = api.getOfferFiles(
                apiKey = apiKey,
                page = page,
                limit = 100,
                offerId = offerId
            ).get().response
            val data: DCMOfferFileLimitedList = getOrThrow(response, "getOfferFiles")

            files = files + data.data.values.toList().map { it.offerFile }
            if (files.size >= data.count) {
                break
            }
            page++
        }

        return files
    }

    private fun <T> getOrThrow(response: DCMResponse<T>, method: String): T {
        if (response.errors.isNotEmpty() || response.data == null) {
            throw DCMException(method = method, errors = response.errors)
        }
        return response.data
    }
}