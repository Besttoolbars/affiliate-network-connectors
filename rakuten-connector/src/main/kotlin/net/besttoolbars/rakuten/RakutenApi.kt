package net.besttoolbars.rakuten

import net.besttoolbars.rakuten.response.*
import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.*
import java.util.concurrent.CompletableFuture

interface RakutenApi {
    @GET("/coupon/1.0")
    fun coupons(
        @Header("Authorization") bearerAuthToken: String,
        @Query("pagenumber") page: Int,
        @Query("mid") mid: String? = null,
        @Query("resultsperpage") resultPerPage: Int = 500
    ): CompletableFuture<RakutenCouponResponse>

    @GET("/linklocator/1.0/getMerchByAppStatus/{status}")
    fun getMerchantByAppStatus(
        @Header("Authorization") bearerAuthToken: String,
        @Path("status") status: String = "approved"
    ): CompletableFuture<RakutenMerchantByStatusResponse>

    @GET("/linklocator/1.0/getTextLinks/{mid}/{creativeCategory}/{startDate}/{endDate}/-1/{page}")
    fun textLinks(
        @Header("Authorization") bearerAuthToken: String,
        @Path("mid") mid: String = "-1",
        @Path("creativeCategory") creativeCategory: String = "-1",
        @Path("startDate") startDate: String = "",
        @Path("endDate") endDate: String = "",
        @Path("page") page: Int = 1
    ): CompletableFuture<RakutenTextLinkResponse>

    @GET("/linklocator/1.0/getBannerLinks/{mid}/{creativeCategory}/{startDate}/{endDate}/-1/-1/{page}")
    fun bannerLinks(
        @Header("Authorization") bearerAuthToken: String,
        @Path("mid") mid: String = "-1",
        @Path("creativeCategory") creativeCategory: String = "-1",
        @Path("startDate") startDate: String = "",
        @Path("endDate") endDate: String = "",
        @Path("page") page: Int = 1
    ): CompletableFuture<RakutenBannerResponse>

    @GET("/productsearch/1.0")
    fun products(
        @Header("Authorization") bearerAuthToken: String,
        @Query("keyword") keyword: String? = null,
        @Query("exact") exact: String? = null,
        @Query("one") one: String? = null,
        @Query("none") none: String? = null,
        @Query("cat") category: String? = null,
        @Query("max") max: Int? = null,
        @Query("pagenumber") pageNumber: Int? = null,
        @Query("mid") merchantId: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("sorttype") sortType: String? = null,
        @Query("language") language: String? = null
    ): CompletableFuture<RakutenProductRootResponse>

    @GET("/advertisersearch/1.0")
    fun merchants(
        @Header("Authorization") bearerAuthToken: String,
        @Query("merchantname") merchantName: String? = null
    ): CompletableFuture<RakutenMerchantRootResponse>

    companion object {
        fun provider(url: String = "https://api.rakutenmarketing.com", client: OkHttpClient? = null): RakutenApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(RakutenApi::class.java)
        }
    }
}
