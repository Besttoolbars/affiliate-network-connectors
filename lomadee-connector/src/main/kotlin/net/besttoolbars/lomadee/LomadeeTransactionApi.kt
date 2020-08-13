package net.besttoolbars.lomadee

import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import net.besttoolbars.lomadee.response.OfferCategoriesResponse
import net.besttoolbars.lomadee.response.OfferStoresResponse
import net.besttoolbars.lomadee.response.OffersResponse
import net.besttoolbars.lomadee.response.TransactionReport
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface LomadeeTransactionApi {
    @GET("/api/lomadee/reportTransaction")
    fun reportTransaction(
        @Query("token") token: String,
        @Query("publisherId") publisherId: String,
        @Query("startDate") startDate: Int,
        @Query("startDate") endDate: Int,
        @Query("eventStatus") eventStatus: Int
    ): CompletableFuture<TransactionReport>

    companion object {
        fun provider(url: String = "https://api.lomadee.com", client: OkHttpClient? = null): LomadeeTransactionApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            client?.let { retrofit.client(it) }
            return retrofit.build().create(LomadeeTransactionApi::class.java)
        }
    }
}