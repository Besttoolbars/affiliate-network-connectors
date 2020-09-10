package net.besttoolbars.awin

import net.besttoolbars.awin.response.AwinAdvertiserDetailsResponse
import net.besttoolbars.awin.response.AwinAdvertiserResponse
import net.besttoolbars.awin.response.AwinOffersResponse
import net.besttoolbars.awin.response.AwinTransactionResponse
import net.besttoolbars.connectors.shared.provideXmlObjectMapper
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
        @Query("relationship") relationship: String? = null,
        @Query("countryCode") countryCode: String? = null
    ): CompletableFuture<AwinAdvertiserResponse>

    @GET("/publishers/{publisher-id}/programmesdetails")
    fun merchants(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("advertiserId") advertiserId: Int
    ): CompletableFuture<AwinAdvertiserDetailsResponse>

    @GET("/publishers/{publisher-id}/commissiongroups?advertiserId=1001")
    fun offers(
        @Path("publisher-id") publisherId: Int,
        @Query("accessToken") token: String,
        @Query("advertiserId") advertiserId: Int
    ): CompletableFuture<AwinOffersResponse>

    @GET("/advertisers/{publisher-id}/transactions/")
    fun transactions(
        @Path("publisher-id") publisherId: Int,
        @Query("startDate") startDate: LocalDateTime,
        @Query("endDate") endDate: LocalDateTime,
        @Query("timezone") timezone: TransactionTimeZone,
        @Query("dateType") dateType: TransactionDateType? = null,
        @Query("status") status: TransactionStatus? = null,
        @Query("advertiserId") advertiserId: Int? = null
    ): CompletableFuture<AwinTransactionResponse>

    companion object {
        fun provider(url: String = "https://api.awin.com", client: OkHttpClient? = null): AwinApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            client?.let { retrofit.client(it) }
            return retrofit.build().create(AwinApi::class.java)
        }
    }
}