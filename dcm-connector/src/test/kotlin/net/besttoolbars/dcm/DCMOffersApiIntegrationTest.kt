package net.besttoolbars.dcm

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

@EnabledIfEnvironmentVariable(named = "DCMApiKey", matches = ".*\\S.*")
class DCMOffersApiIntegrationTest {
    val api = DCMApi.provider()
    val apiKey = System.getenv("DCMApiKey")

    @Test
    fun getOffers() {
        val response = api.getApprovedOffers(
            apiKey = apiKey,
            page = 1,
            limit = 10000
        ).get()
        Assertions.assertNotNull(response.response.data)
    }

    @Test
    fun getCategories() {
        val offers = api.getApprovedOffers(
            apiKey = apiKey,
            page = 1,
            limit = 100
        ).get()

        val response = api.getCategories(
            apiKey = apiKey,
            ids = offers.response.data!!.data.values.map { it.offer.id }
        ).get()

        Assertions.assertNotNull(response.response.data)
    }

    @Test
    fun getOfferUrls() {
        val response = api.getOffersUrls(
            apiKey = apiKey,
            page = 1,
            limit = 100
        ).get()

        Assertions.assertNotNull(response.response.data)
    }

    @Test
    fun getOfferFiles() {
        val response = api.getOfferFiles(
            apiKey = apiKey,
            page = 1,
            limit = 100
        ).get()

        Assertions.assertNotNull(response.response.data)
    }
}