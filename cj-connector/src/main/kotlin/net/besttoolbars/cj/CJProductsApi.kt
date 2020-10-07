package net.besttoolbars.cj

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.cj.response.CjGraphQlResponse
import net.besttoolbars.cj.response.CJShoppingProductsResponse
import net.besttoolbars.connectors.shared.graphql.GraphQLBodyConverter
import net.besttoolbars.connectors.shared.graphql.GraphQL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.CompletableFuture

interface CJProductsApi {
    @POST("/query")
    fun shoppingProducts(
            @Header("Authorization") authorization: String,
            @Body @GraphQL query: String
    ): CompletableFuture<CjGraphQlResponse<CJShoppingProductsResponse>>

    companion object {
        private fun provideJsonMapper() = jacksonObjectMapper().apply {
            registerModule(JavaTimeModule())
        }

        fun provider(url: String = "https://ads.api.cj.com", client: OkHttpClient? = null): CJProductsApi {
            val objectMapper = provideJsonMapper()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GraphQLBodyConverter())
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .baseUrl(url)
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(CJProductsApi::class.java)
        }
    }
}