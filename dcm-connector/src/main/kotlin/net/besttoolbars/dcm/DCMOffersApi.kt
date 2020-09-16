package net.besttoolbars.dcm

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.dcm.response.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface DCMOffersApi {
    // contain, sort

    @GET("/")
    fun getApprovedOffers(
        @Query("api_key") apiKey: String,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "findMyApprovedOffers",
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CompletableFuture<DCMApiResponse<DCMLimitedList<DCMOfferListData>>>

    @GET("/")
    fun getCategories(
        @Query("api_key") apiKey: String,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "getCategories",
        @Query("ids[]") ids: List<Int>
    ): CompletableFuture<DCMApiResponse<List<DCMOfferCategory>>>

    @GET("/")
    fun getOffersUrls(
        @Query("api_key") apiKey: String,
        @Query("Target") target: String = "Affiliate_OfferUrl",
        @Query("Method") method: String = "findAll",
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[id]") id: Int? = null,
        @Query("filters[created]") created: String? = null,
        @Query("filters[modified]") modified: String? = null,
        @Query("filters[name]") name: String? = null,
        @Query("filters[offer_id]") offerId: Int? = null,
        @Query("filters[preview_url]") previewUrl: String? = null
    ): CompletableFuture<DCMApiResponse<DCMLimitedList<DCMOfferUrlListData>>>

    @GET("/")
    fun getOfferFiles(
        @Query("api_key") apiKey: String,
        @Query("Target") target: String = "Affiliate_OfferFile",
        @Query("Method") method: String = "findAll",
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[id]") id: Int? = null,
        @Query("filters[created]") created: String? = null,
        @Query("filters[modified]") modified: String? = null,
        @Query("filters[name]") name: String? = null,
        @Query("filters[offer_id]") offerId: Int? = null,
        @Query("filters[preview_url]") previewUrl: String? = null
    ): CompletableFuture<DCMApiResponse<DCMLimitedList<DCMOfferFileListData>>>

    companion object {
        fun provider(url: String = "https://dcm.api.hasoffers.com/Apiv3/json", client: OkHttpClient? = null): DCMOffersApi {
            val objectMapper = jacksonObjectMapper()
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
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