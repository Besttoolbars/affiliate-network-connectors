package net.besttoolbars.dcm

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.dcm.response.DCMApiResponse
import net.besttoolbars.dcm.response.DCMOfferList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface DCMOffersApi {
    @GET("/")
    fun getOffers(
        @Query("api_key") apiKey: String,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "findMyApprovedOffers",
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CompletableFuture<DCMApiResponse<DCMOfferList>>

    companion object {
        fun provider(url: String = "https://dcm.api.hasoffers.com/Apiv3/json", client: OkHttpClient? = null): DCMOffersApi {
            val objectMapper = jacksonObjectMapper()
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(DCMOffersApi::class.java)
        }
    }
}