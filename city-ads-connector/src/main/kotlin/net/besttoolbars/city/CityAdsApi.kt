package net.besttoolbars.city

import net.besttoolbars.city.response.CityAdsCoupon
import net.besttoolbars.city.response.CityAdsOffer
import net.besttoolbars.city.response.CityAdsPaginationListResponse
import net.besttoolbars.city.response.CityAdsPaginationMapResponse
import net.besttoolbars.connectors.shared.CommaSeparatedValues
import net.besttoolbars.connectors.shared.provideJsonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface CityAdsApi {
    @GET("/api/rest/webmaster/json/offers/{type}")
    fun offers(
        @Path("type") offerType: CityAdsOfferType = CityAdsOfferType.WEB,
        @Query("remote_auth") token: String,
        @Query("start") page: Int = 0,
        @Query("limit") limit: Int = 100,
        @Query("geo") geo: CommaSeparatedValues<Int>? = null,
        @Query("user_has_offer") hasOffer: Boolean = true,
        @Query("lang") responseLang: String = "en"
    ): CompletableFuture<CityAdsPaginationMapResponse<CityAdsOffer>>

    @GET("/api/rest/webmaster/json/coupons")
    fun coupons(
        @Query("remote_auth") token: String,
        @Query("start") page: Int = 0,
        @Query("limit") limit: Int = 100,
        @Query("geo") geo: CommaSeparatedValues<Int>? = null,
        @Query("lang") responseLang: String = "en",
        @Query("coupon_lang") couponLang: String? = null
    ): CompletableFuture<CityAdsPaginationListResponse<CityAdsCoupon>>

    companion object {
        fun provide(url: String = "https://cityads.com", client: OkHttpClient? = null): CityAdsApi {
            val objectMapper = provideJsonObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            client?.let { retrofit.client(client) }
            return retrofit.build().create(CityAdsApi::class.java)
        }
    }
}

enum class CityAdsOfferType {
    WEB,
    WEB_FAVOURITE,
    MOBILE,
    MOBILE_FAVOURITE,
    API,
    API_FAVOURITE,
    ALL,
    ALL_FAVOURITE;

    override fun toString(): String = name.toLowerCase()
}