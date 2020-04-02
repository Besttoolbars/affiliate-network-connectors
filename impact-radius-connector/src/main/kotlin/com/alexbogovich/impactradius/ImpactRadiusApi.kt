package com.alexbogovich.impactradius


import com.alexbogovich.impactradius.response.ImpactRadiusAdsResponse
import com.alexbogovich.impactradius.response.ImpactRadiusCampaignsResponse
import com.alexbogovich.impactradius.response.ImpactRadiusPromoAdsResponse
import com.alexbogovich.shared.provideXmlObjectMapper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface ImpactRadiusApi {
    @GET("/Mediapartners/{accountSid}/Campaigns")
    fun campaigns(
        @Header("Authorization") basicAuthToken: String,
        @Path("accountSid") accountSid: String,
        @Query("page") page: Int
    ): CompletableFuture<ImpactRadiusCampaignsResponse>

    @GET("/Mediapartners/{accountSid}/PromoAds")
    fun promoAds(
        @Header("Authorization") basicAuthToken: String,
        @Path("accountSid") accountSid: String,
        @Query("page") page: Int
    ): CompletableFuture<ImpactRadiusPromoAdsResponse>

    @GET("/Mediapartners/{accountSid}/Ads")
    fun ads(
        @Header("Authorization") basicAuthToken: String,
        @Path("accountSid") accountSid: String,
        @Query("page") page: Int
    ): CompletableFuture<ImpactRadiusAdsResponse>

    companion object {
        fun provider(url: String = "https://api.impactradius.com"): ImpactRadiusApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            return retrofit.build().create(ImpactRadiusApi::class.java)
        }
    }
}
