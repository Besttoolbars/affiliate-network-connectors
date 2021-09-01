package net.besttoolbars.awin

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.awin.response.AwinAdvertiserDetailsResponse
import net.besttoolbars.awin.response.AwinAdvertiserResponse
import net.besttoolbars.awin.response.AwinCommissionGroupsResponse
import net.besttoolbars.awin.response.AwinTransactionResponse
import net.besttoolbars.connectors.shared.RateLimitConfig
import net.besttoolbars.connectors.shared.provideHttpClientWithRateLimit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture

interface AwinApi {
    @GET("/publishers/{publisher-id}/programmes")
    fun merchants(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("relationship") relationship: AwinRelationship? = null,
        @Query("countryCode") countryCode: String? = null
    ): CompletableFuture<AwinAdvertiserResponse>

    @GET("/publishers/{publisher-id}/programmedetails")
    fun merchants(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("advertiserId") advertiserId: Int
    ): CompletableFuture<AwinAdvertiserDetailsResponse>

    @GET("/publishers/{publisher-id}/commissiongroups")
    fun commissionGroups(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("advertiserId") advertiserId: Int
    ): CompletableFuture<AwinCommissionGroupsResponse>

    @GET("/publishers/{publisher-id}/transactions/")
    fun transactions(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("startDate") startDate: LocalDateTime,
        @Query("endDate") endDate: LocalDateTime,
        @Query("timezone") timezone: AwinTransactionTimeZone = AwinTransactionTimeZone.UTC,
        @Query("dateType") dateType: AwinTransactionDateType? = null,
        @Query("status") status: AwinTransactionStatus? = null,
        @Query("advertiserId") advertiserId: Int? = null,
        @Query("showBasketProducts") showBasketProducts: Boolean = false
    ): CompletableFuture<AwinTransactionResponse>

    companion object {
        private fun provideJsonMapper() = jacksonObjectMapper().apply {
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            registerModule(JavaTimeModule())
        }

        fun provider(
            url: String = "https://api.awin.com",
            client: OkHttpClient? = null,
            config: RateLimitConfig = RateLimitConfig(20, Duration.ofMinutes(1))
        ): AwinApi {
            val objectMapper = provideJsonMapper()
            val httpClient = provideHttpClientWithRateLimit(config, client)
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            return retrofit.build().create(AwinApi::class.java)
        }
    }
}