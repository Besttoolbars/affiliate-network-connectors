package net.besttoolbars.admitad

import net.besttoolbars.admitad.response.AdmitadAdvResponse
import net.besttoolbars.admitad.response.AdmitadCouponResponse
import net.besttoolbars.admitad.response.OAuth2Token
import net.besttoolbars.connectors.shared.RateLimitConfig
import net.besttoolbars.connectors.shared.provideHttpClientWithRateLimit
import net.besttoolbars.connectors.shared.provideJsonObjectMapper
import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.*
import java.time.Duration
import java.util.concurrent.CompletableFuture

interface AdmitadApi {
    @GET("/advcampaigns/website/{website-id}/")
    fun adv(
        @Header("Authorization") authorization: String,
        @Path("website-id") websiteId: String,
        @Query("connection_status") connectionStatus: ConnectionStatus? = null,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 500
    ): CompletableFuture<AdmitadAdvResponse>

    @GET("/coupons/website/{website-id}/")
    fun coupons(
        @Header("Authorization") authorization: String,
        @Path("website-id") websiteId: String,
        @Query("language") language: String?,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 500
    ): CompletableFuture<AdmitadCouponResponse>

    @POST("/token/")
    @FormUrlEncoded
    fun token(
        @Header("Authorization")  authorization: String,
        @Field("client_id")  clientId: String,
        @Field("scope")  scope: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): CompletableFuture<OAuth2Token>

    companion object {
        fun provider(
            url: String = "https://api.admitad.com",
            client: OkHttpClient? = null,
            config: RateLimitConfig = RateLimitConfig(25, Duration.ofMinutes(1))
        ): AdmitadApi {
            val objectMapper = provideJsonObjectMapper()
            val httpClient = provideHttpClientWithRateLimit(config, client)
            val retrofit = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            return retrofit.build().create(AdmitadApi::class.java)
        }
    }

    enum class ConnectionStatus {
        active, pending, declined
    }
}


