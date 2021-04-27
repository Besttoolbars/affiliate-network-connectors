package net.besttoolbars.dcm

import net.besttoolbars.connectors.shared.RateLimitConfig
import net.besttoolbars.connectors.shared.provideHttpClientWithRateLimit
import net.besttoolbars.dcm.dto.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Duration
import java.util.concurrent.CompletableFuture

interface DCMApi {
    @GET("/Apiv3/json")
    fun getApprovedOffers(
        @Query("api_key") apiKey: String,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "findMyApprovedOffers"
    ): CompletableFuture<DCMApiResponse<Map<String, DCMOfferListData>>>

    @GET("/Apiv3/json")
    fun getCategories(
        @Query("api_key") apiKey: String,
        @Query("ids[]") ids: List<Int>,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "getCategories"
    ): CompletableFuture<DCMApiResponse<List<DCMOfferCategory>>>

    @GET("/Apiv3/json")
    fun getOffersUrls(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[id]") id: Int? = null,
        @Query("filters[created]") created: String? = null,
        @Query("filters[modified]") modified: String? = null,
        @Query("filters[name]") name: String? = null,
        @Query("filters[offer_id]") offerId: Int? = null,
        @Query("filters[preview_url]") previewUrl: String? = null,
        @Query("Target") target: String = "Affiliate_OfferUrl",
        @Query("Method") method: String = "findAll"
    ): CompletableFuture<DCMApiResponse<DCMOfferUrlLimitedList>>

    @GET("/Apiv3/json")
    fun getOfferFiles(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[id]") id: Int? = null,
        @Query("filters[created]") created: String? = null,
        @Query("filters[modified]") modified: String? = null,
        @Query("filters[name]") name: String? = null,
        @Query("filters[offer_id]") offerId: Int? = null,
        @Query("filters[preview_url]") previewUrl: String? = null,
        @Query("Target") target: String = "Affiliate_OfferFile",
        @Query("Method") method: String = "findAll"
    ): CompletableFuture<DCMApiResponse<DCMOfferFileLimitedList>>

    @GET("/Apiv3/json")
    fun getThumbnails(
        @Query("api_key") apiKey: String,
        @Query("ids[]") ids: List<Int>,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "getThumbnail"
    ): CompletableFuture<DCMApiResponse<List<DCMOfferThumbnail>>>

    @GET("/Apiv3/json")
    fun getAllReceipts(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[date]") filterByDate: String,
        @Query("sort[date]") sortByDate: String = "asc",
        @Query("Target") target: String = "Affiliate_AffiliateBilling",
        @Query("Method") method: String = "findAllReceipts"
    ): CompletableFuture<DCMApiResponse<DCMReceiptsLimitedList>>

    @GET("/Apiv3/json")
    fun generateTrackingLink(
        @Query("api_key") apiKey: String,
        @Query("offer_id") offerId: Int,
        @Query("params[aff_click_id]") transactionId: String?,
        @Query("params[url_id]") offerUrlId: Int? = null,
        @Query("Target") target: String = "Affiliate_Offer",
        @Query("Method") method: String = "generateTrackingLink"
    ): CompletableFuture<DCMApiResponse<DCMTrackingLink>>

    @GET("/Apiv3/json")
    fun getConversationReports(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("filters[Stat.date][values]") date: String,

        @Query("fields[]") field1: String = "Stat.id",
        @Query("fields[]") field2: String = "ConversionsMobile.affiliate_click_id",
        @Query("fields[]") field3: String = "Stat.conversion_status",
        @Query("fields[]") field4: String = "Stat.currency",
        @Query("fields[]") field5: String = "Stat.datetime",
        @Query("fields[]") field6: String = "Stat.offer_id",
        @Query("fields[]") field7: String = "Stat.offer_url_id",
        @Query("fields[]") field8: String = "Stat.payout",
        @Query("fields[]") field9: String = "Stat.affiliate_info1",

        @Query("filters[Stat.date][conditional]") dateSortBy: String = "GREATER_THAN_OR_EQUAL_TO",
        @Query("Target") target: String = "Affiliate_Report",
        @Query("Method") method: String = "getConversions"
    ): CompletableFuture<DCMApiResponse<DCMConversionReportListData>>

    companion object {
        fun provider(
            url: String = "https://dcm.api.hasoffers.com/",
            client: OkHttpClient? = null,
            config: RateLimitConfig = RateLimitConfig(50, Duration.ofSeconds(10))
        ): DCMApi {
            val objectMapper = getDCMMapper()
            val httpClient = provideHttpClientWithRateLimit(config, client)
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            return retrofit.build().create(DCMApi::class.java)
        }
    }
}