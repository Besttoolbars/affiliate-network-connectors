package net.besttoolbars.dcm

import net.besttoolbars.connectors.shared.RateLimitConfig
import net.besttoolbars.connectors.shared.provideHttpClientWithRateLimit
import net.besttoolbars.dcm.dto.DCMCampaignsResponse
import net.besttoolbars.dcm.dto.DCMCodesResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Duration
import java.util.concurrent.CompletableFuture

interface PartnerDCMApi {
    @GET("rest/campaign")
    fun getCampaigns(
        @Query("api_key") apiKey: String
    ): CompletableFuture<DCMCampaignsResponse>

    @GET("rest/campaigncode")
    fun getCodes(
        @Query("api_key") apiKey: String,
        @Query("q.campaignId") campaignId: Int? = null
    ): CompletableFuture<DCMCodesResponse>

    companion object {
        fun provider(
            url: String = "https://partner.dcmnetwork.com/publisher/",
            client: OkHttpClient? = null,
            config: RateLimitConfig = RateLimitConfig(50, Duration.ofSeconds(10))
        ): PartnerDCMApi {
            val objectMapper = getDCMMapper()
            val httpClient = provideHttpClientWithRateLimit(config, client)
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            return retrofit.build().create(PartnerDCMApi::class.java)
        }
    }
}