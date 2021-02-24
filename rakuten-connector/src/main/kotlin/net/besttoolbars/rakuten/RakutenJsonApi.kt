package net.besttoolbars.rakuten

import net.besttoolbars.connectors.shared.provideJsonObjectMapper
import net.besttoolbars.rakuten.response.RakutenTransactionEvent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface RakutenJsonApi {
    @GET("/events/1.0/transactions")
    fun transactions(
        @Header("Authorization") bearerAuthToken: String,
        @Query("process_date_start") processDateStart: String? = null,
        @Query("process_date_end") processDateEnd: String? = null,
        @Query("transaction_date_start") transactionDateStart: String? = null,
        @Query("transaction_date_end") transactionDateEnd: String? = null,
        @Query("limit") limit: String? = null,
        @Query("page") page: String? = null,
        @Query("currency") currency: String? = null,
        @Query("type") type: String? = null
    ): CompletableFuture<List<RakutenTransactionEvent>>

    companion object {
        fun provider(url: String = "https://api.rakutenmarketing.com", client: OkHttpClient? = null): RakutenJsonApi {
            val objectMapper = provideJsonObjectMapper()
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .apply { client?.let { client(it) } }
                .build()
                .create(RakutenJsonApi::class.java)
        }
    }
}