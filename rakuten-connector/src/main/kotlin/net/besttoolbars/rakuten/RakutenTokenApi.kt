package net.besttoolbars.rakuten

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.rakuten.response.OAuth2Token
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.CompletableFuture

interface RakutenTokenApi {
    @FormUrlEncoded
    @POST("/token")
    fun token(
        @Header("Authorization") basicAuthToken: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("scope") scope: String,
        @Field("grant_type") grantType: String = "password"
    ): CompletableFuture<OAuth2Token>

    companion object {
        fun provider(url: String = "https://api.rakutenmarketing.com", client: OkHttpClient? = null): RakutenTokenApi {
            val objectMapper = jacksonObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(RakutenTokenApi::class.java)
        }
    }
}