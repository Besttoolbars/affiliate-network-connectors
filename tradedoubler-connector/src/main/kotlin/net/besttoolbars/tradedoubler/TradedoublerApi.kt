package net.besttoolbars.tradedoubler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.tradedoubler.response.TradedoublerOfferResponse
import net.besttoolbars.tradedoubler.response.TradedoublerProductResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface TradedoublerApi {
    @GET("/1.0/vouchers.json;keywords={keywords};startDate={start-date}")
    fun offers(
        @Query("token") token: String,
        @Path("keywords") keywords: String? = null,
        @Path("start-date") startDate: String? = null
    ): CompletableFuture<TradedoublerOfferResponse>

    /*
     * more parameters can be found http://dev.tradedoubler.com/products/publisher/#Overview
     */
    @GET("/1.0/products;q={query};page={page};pageSize={page-size}")
    fun products(
        @Query("token") token: String,
        @Path("query") query: String? = null,
        @Path("page") page: Int? = null,
        @Path("page-size") pageSize: Int? = null
    ): CompletableFuture<TradedoublerProductResponse>



    companion object {
        fun provider(url: String = "http://api.tradedoubler.com", client: OkHttpClient? = null): TradedoublerApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            client?.let { retrofit.client(it) }
            return retrofit.build().create(TradedoublerApi::class.java)
        }
    }
}