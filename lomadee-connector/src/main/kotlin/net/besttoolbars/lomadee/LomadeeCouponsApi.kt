package net.besttoolbars.lomadee

import net.besttoolbars.lomadee.response.CouponCategoryResponse
import net.besttoolbars.lomadee.response.CouponResponse
import net.besttoolbars.lomadee.response.CouponStoreResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface LomadeeCouponsApi {
    @GET("/v2/{app-token}/coupon/_categories")
    fun categories(
        @Path("app-token") token: String,
        @Query("sourceId") source: String,
        @Query("storeId") storeId: Int? = null
    ): CompletableFuture<CouponCategoryResponse>

    @GET("/v2/{app-token}/coupon/_stores")
    fun stores(
        @Path("app-token") token: String,
        @Query("sourceId") source: String,
        @Query("categoryId") categoryId: Int? = null
    ): CompletableFuture<CouponStoreResponse>

    @GET("/v2/{app-token}/coupon/_all")
    fun coupons(
        @Path("app-token") token: String,
        @Query("sourceId") source: String,
        @Query("storeId") storeId: Int? = null,
        @Query("categoryId") categoryId: Int? = null
    ): CompletableFuture<CouponResponse>

    companion object {
        fun provider(url: String = "https://api.lomadee.com", client: OkHttpClient? = null): LomadeeCouponsApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
            client?.let { retrofit.client(it) }
            return retrofit.build().create(LomadeeCouponsApi::class.java)
        }
    }
}