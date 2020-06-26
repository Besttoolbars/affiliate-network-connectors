package net.besttoolbars.rakuten

import net.besttoolbars.rakuten.response.*
import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.rakuten.response.*
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

interface RakutenTokenApi {
    @FormUrlEncoded
    @POST("/token")
    fun token(
        @Header("Authorization") basicAuthToken: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("scope") scope: String,
        @Field("grant_type") grantType: String = "password"
    ): CompletableFuture<OAuth2Token>

    companion object {
        fun provider(url: String = "https://api.rakutenmarketing.com", client: OkHttpClient? = null): RakutenTokenApi {
            val objectMapper = jacksonObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(RakutenTokenApi::class.java)
        }
    }
}
