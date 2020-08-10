package net.besttoolbars.afilio

import net.besttoolbars.afilio.response.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

interface AfilioApi {
    @GET("/api/feedproducts.php")
    fun coupons(
        @Query("token") token: String,
        @Query("affid") affId: Int,
        @Query("siteid") siteId: String,
        @Query("format") format: Format = Format.JSON,
        @Query("mode") mode: GetCouponsMode = GetCouponsMode.dl
    ): CompletableFuture<List<Coupon>>

    @GET("/api/prog_api.php")
    fun merchants(
        @Query("token") token: String,
        @Query("affid") affId: Int
    ): CompletableFuture<Map<String, Merchant>>

    @GET("/api/leadsale_api.php")
    fun transactions(
        @Query("token") token: String,
        @Query("affid") affId: Int,
        @Query("dateStart") dateStart: LocalDate,
        @Query("dateEnd") dateEnd: LocalDate,
        @Query("dateType") dateType: DateType = DateType.Transaction,
        @Query("format") format: Format = Format.JSON,
        @Query("mode") mode: GetTransactionMode = GetTransactionMode.list
    ): CompletableFuture<TransactionResponse>

    companion object {
        fun provider(url: String = "https://v2.afilio.com.br", client: OkHttpClient? = null): AfilioApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
            client?.let{ retrofit.client(it) }
            return retrofit.build().create(AfilioApi::class.java)
        }
    }
}