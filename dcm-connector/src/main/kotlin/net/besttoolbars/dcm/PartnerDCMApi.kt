package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.DCMCampaignsResponse
import net.besttoolbars.dcm.dto.DCMCodesResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
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
        fun provider(url: String = "https://partner.dcmnetwork.com/publisher/", client: OkHttpClient? = null): PartnerDCMApi {
            val objectMapper = getDCMMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(PartnerDCMApi::class.java)
        }
    }
}