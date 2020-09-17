package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.*

class DCMCompositeApi(
    private val api: DCMOffersRawApi
) {
    fun getOffersWithAttachedData(
        apiKey: String,
        page: Int,
        limit: Int
    ): DCMOfferWithAttachedList {
        val offersResponse = api.getApprovedOffers(
            apiKey = apiKey,
            limit = limit,
            page = page
        ).get().response
        val offersData = getOrThrow(offersResponse, "getApprovedOffers")
        val offers = offersData.data.values

        val categories = getCategories(apiKey, offers.map { it.offer.id })

        val items =  offers.map {
            val offer = it.offer

            val offerCategories = categories.find { it.offerId == offer.id }?.categories?.values?.toList() ?: emptyList()

            DCMOfferWithAttached(
                offer = offer,
                offerUrls = getOfferUrls(apiKey, offer.id),
                offerFiles = getOfferFiles(apiKey, offer.id),
                categories = offerCategories
            )
        }

        return DCMOfferWithAttachedList(
            items = items,
            totalCount = offersData.count
        )
    }

    fun getCategories(
        apiKey: String,
        offerIds: List<Int>
    ): List<DCMOfferCategory> {
        val response = api.getCategories(
            apiKey = apiKey,
            ids = offerIds
        ).get().response
        return getOrThrow(response, "getCategories")
    }

    fun getOfferUrls(
        apiKey: String,
        offerId: Int
    ): List<DCMOfferUrl> {
        var urls = listOf<DCMOfferUrl>()
        var page = 1

        while (true) {
            val response = api.getOffersUrls(
                apiKey = apiKey,
                page = page,
                limit = 100,
                offerId = offerId
            ).get().response
            val data = getOrThrow(response, "getOffersUrls")

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
            val response = api.getOfferFiles(
                apiKey = apiKey,
                page = page,
                limit = 100,
                offerId = offerId
            ).get().response
            val data = getOrThrow(response, "getOfferFiles")

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