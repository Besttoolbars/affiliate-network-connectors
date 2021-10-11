package net.besttoolbars.pdlprofit

import net.besttoolbars.connectors.shared.provideJsonObjectMapper
import net.besttoolbars.pdlprofit.response.PdlProfitConversion
import net.besttoolbars.pdlprofit.response.PdlProfitOffer
import net.besttoolbars.pdlprofit.response.PdlProfitResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

interface PdlProfitApi {
    @GET("/partnerapi/offers/data")
    fun offers(
        @Query("api_key") apiKey: String,
        @Query("p") page: Int = 1,
        @Query("pp") pageSize: Int = 100,
        @Query("country") country: String? = null,
        @Query("mode") leadType: String? = null
    ): CompletableFuture<PdlProfitResponse<PdlProfitOffer>>

    @GET("/partnerapi/postbacks")
    fun conversions(
        @Query("api_key") apiKey: String,
        @Query("from") from: LocalDate,
        @Query("to") to: LocalDate,
        @Query("p") page: Int = 1,
        @Query("pp") pageSize: Int = 100,
        @Query("country") country: String? = null
    ): CompletableFuture<PdlProfitResponse<PdlProfitConversion>>

    companion object {
        fun create(url: String = "https://pdl-profit.com", client: OkHttpClient? = null): PdlProfitApi =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(provideJsonObjectMapper()))
                .apply { if (client != null) client(client) }
                .build()
                .create(PdlProfitApi::class.java)
    }
}