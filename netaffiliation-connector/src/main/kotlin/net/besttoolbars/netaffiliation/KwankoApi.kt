package net.besttoolbars.netaffiliation

import net.besttoolbars.connectors.shared.provideXmlObjectMapper
import net.besttoolbars.netaffiliation.response.RssPromoCodes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface KwankoApi {
    @GET("/rsscp.php")
    fun rssCoupons(@Query("sec") sec: String): CompletableFuture<RssPromoCodes>

    companion object {
        fun provider(url: String = "https://flux.netaffiliation.com", client: OkHttpClient? = null): KwankoApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            client?.let{ retrofit.client(client) }
            return retrofit.build().create(KwankoApi::class.java)
        }
    }
}