package net.besttoolbars.awin

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.awin.response.AwinAdvertiserDetailsResponse
import net.besttoolbars.awin.response.AwinAdvertiserResponse
import net.besttoolbars.awin.response.AwinOffersResponse
import net.besttoolbars.awin.response.AwinTransactionResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
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
    fun offers(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("advertiserId") advertiserId: Int
    ): CompletableFuture<AwinOffersResponse>

    @GET("/publishers/{publisher-id}/transactions/")
    fun transactions(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("startDate") startDate: LocalDateTime,
        @Query("endDate") endDate: LocalDateTime,
        @Query("timezone") timezone: TransactionTimeZone,
        @Query("dateType") dateType: TransactionDateType? = null,
        @Query("status") status: TransactionStatus? = null,
        @Query("advertiserId") advertiserId: Int? = null
    ): CompletableFuture<AwinTransactionResponse>

    companion object {
        private fun provideJsonMapper() = jacksonObjectMapper().apply {
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            registerModule(JavaTimeModule())
        }

        fun provider(url: String = "https://api.awin.com", client: OkHttpClient? = null): AwinApi {
            val objectMapper = provideJsonMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            client?.let { retrofit.client(it) }
            return retrofit.build().create(AwinApi::class.java)
        }
    }
}