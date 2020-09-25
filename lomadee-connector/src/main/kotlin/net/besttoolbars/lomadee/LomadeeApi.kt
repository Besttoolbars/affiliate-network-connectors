package net.besttoolbars.lomadee

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.lomadee.response.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface LomadeeApi : LomadeeOffersApi, LomadeeCouponsApi {
    @GET("/api/lomadee/createToken")
    fun createToken(
        @Query("user") user: String,
        @Query("password") password: String
    ): CompletableFuture<Token>

    companion object {
        fun provider(url: String = "https://api.lomadee.com", client: OkHttpClient? = null): LomadeeApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            client?.let { retrofit.client(it) }
            return retrofit.build().create(LomadeeApi::class.java)
        }
    }
}