package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface DCMApi {
    @GET("/Apiv3/json")
    fun getApprovedOffers(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "findMyApprovedOffers"
    ): CompletableFuture<DCMApiResponse<DCMOfferLimitedList>>

    @GET("/Apiv3/json")
    fun getCategories(
        @Query("api_key") apiKey: String,
        @Query("ids[]") ids: List<Int>,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "getCategories"
    ): CompletableFuture<DCMApiResponse<List<DCMOfferCategory>>>

    @GET("/Apiv3/json")
    fun getOffersUrls(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[id]") id: Int? = null,
        @Query("filters[created]") created: String? = null,
        @Query("filters[modified]") modified: String? = null,
        @Query("filters[name]") name: String? = null,
        @Query("filters[offer_id]") offerId: Int? = null,
        @Query("filters[preview_url]") previewUrl: String? = null,
        @Query("Target") target: String = "Affiliate_OfferUrl",
        @Query("Method") method: String = "findAll"
    ): CompletableFuture<DCMApiResponse<DCMOfferUrlLimitedList>>

    @GET("/Apiv3/json")
    fun getOfferFiles(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[id]") id: Int? = null,
        @Query("filters[created]") created: String? = null,
        @Query("filters[modified]") modified: String? = null,
        @Query("filters[name]") name: String? = null,
        @Query("filters[offer_id]") offerId: Int? = null,
        @Query("filters[preview_url]") previewUrl: String? = null,
        @Query("Target") target: String = "Affiliate_OfferFile",
        @Query("Method") method: String = "findAll"
    ): CompletableFuture<DCMApiResponse<DCMOfferFileLimitedList>>

    @GET("/Apiv3/json")
    fun getThumbnails(
        @Query("api_key") apiKey: String,
        @Query("ids[]") ids: List<Int>,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "getThumbnail"
    ): CompletableFuture<DCMApiResponse<List<DCMOfferThumbnail>>>

    @GET("/Apiv3/json")
    fun getAllReceipts(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("Target") target: String = "Affiliate_AffiliateBilling",
        @Query("Method") method: String = "findAllReceipts"
    ): CompletableFuture<DCMApiResponse<DCMReceiptsLimitedList>>

    companion object {
        fun provider(url: String = "https://dcm.api.hasoffers.com/", client: OkHttpClient? = null): DCMApi {
            val objectMapper = getDCMMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(DCMApi::class.java)
        }
    }
}