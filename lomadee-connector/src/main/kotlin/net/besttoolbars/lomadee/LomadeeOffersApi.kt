package net.besttoolbars.lomadee

import net.besttoolbars.lomadee.response.OfferCategoriesResponse
import net.besttoolbars.lomadee.response.OfferStoresResponse
import net.besttoolbars.lomadee.response.OffersResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface LomadeeOffersApi {
    @GET("/v3/{app-token}/category/_all")
    fun offerCategories(
        @Path("app-token") token: String,
        @Query("sourceId") source: String,
        @Query("storeId") storeId: Int? = null,
        @Query("hasOffer") hasOffer: Boolean? = null
    ): CompletableFuture<OfferCategoriesResponse>

    @GET("/v3/{app-token}/store/_all")
    fun offerStores(
        @Path("app-token") token: String,
        @Query("sourceId") source: String,
        @Query("storeId") storeId: Int? = null,
        @Query("hasOffer") hasOffer: Boolean? = null
    ): CompletableFuture<OfferStoresResponse>

    @GET("/v3/{app-token}/offer/_store/{storeId}")
    fun offersByStore(
        @Path("app-token") token: String,
        @Path("storeId") storeId: Int,
        @Query("sourceId") source: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 50,
        @Query("categoryId") categoryId: Int? = null
    ): CompletableFuture<OffersResponse>

    @GET("/v3/{app-token}/offer/_category/{categoryId}")
    fun offersByCategory(
        @Path("app-token") token: String,
        @Path("categoryId") categoryId: Int,
        @Query("sourceId") source: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 50,
        @Query("storeId") storeId: Int? = null
    ): CompletableFuture<OffersResponse>
}