package com.alexbogovich.cj

import com.alexbogovich.cj.response.CjLinksResponse
import com.alexbogovich.shared.provideXmlObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface CJLinksApi {
    @GET("/v2/link-search")
    fun links(
        @Header("Authorization") authorization: String,
        @Query("website-id") websiteId: String,
        @Query("page-number") page: Int,
        @Query("records-per-page") size: Int,
        @Query("link-type") linkType: String,
        @Query("advertiser-ids") advertiserIds: String? = "joined",
        @Query("language") language: String? = null,
        @Query("promotion-type") promotionType: String? = null,
        @Query("category") category: String? = null,
        @Query("keywords") keywords: String? = null
    ): CompletableFuture<CjLinksResponse>

    companion object {
        fun provider(url: String = "https://link-search.api.cj.com", client: OkHttpClient? = null): CJLinksApi {
            val objectMapper = provideXmlObjectMapper()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(CJLinksApi::class.java)
        }
    }
}
