package net.besttoolbars.pdlprofit.response


import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PdlProfitResponse<T>(
    val status: String,
    @JsonAlias("meassage", "message")
    val message: String,
    val count: Int? = null,
    val pages: Int? = null,
    val currentPage: Int? = null,
    val data: List<T>? = null
) {
    val hasNextPage = currentPage != null && pages != null && currentPage < pages
}