package net.besttoolbars.cj

import net.besttoolbars.cj.response.CjAdvertisersResponse
import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface CJAdvertiserApi {
    @GET("/v2/advertiser-lookup")
    fun advertisers(
        @Header("Authorization") authorization: String,
        @Query("requestor-cid") requestorCid: String,
        @Query("page-number") page: Int,
        @Query("records-per-page") size: Int,
        @Query("advertiser-ids") advertiserIds: String = "joined",
        @Query("advertiser-name") advertiserName: String? = null,
        @Query("keywords") keywords: String? = null
    ): CompletableFuture<CjAdvertisersResponse>

    companion object {
        fun provider(url: String = "https://advertiser-lookup.api.cj.com", client: OkHttpClient? = null): CJAdvertiserApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(CJAdvertiserApi::class.java)
        }
    }
}
