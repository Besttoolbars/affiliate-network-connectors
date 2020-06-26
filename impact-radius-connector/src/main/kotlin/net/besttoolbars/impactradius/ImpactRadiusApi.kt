package net.besttoolbars.impactradius


import net.besttoolbars.impactradius.response.ImpactRadiusAdsResponse
import net.besttoolbars.impactradius.response.ImpactRadiusCampaignsResponse
import net.besttoolbars.impactradius.response.ImpactRadiusPromoAdsResponse
import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import okhttp3.OkHttpClient
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
        fun provider(url: String = "https://api.impactradius.com", client: OkHttpClient? = null): ImpactRadiusApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(ImpactRadiusApi::class.java)
        }
    }
}
