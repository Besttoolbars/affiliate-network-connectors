package net.besttoolbars.afilio.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Merchant(
    val id: String,
    @JsonProperty("nom")
    val name: String,
    val url: String,
    val description: String,
    @JsonProperty("siteid")
    val siteId: String,
    @JsonProperty("progid")
    val progId: String,
    @JsonProperty("cpmprice")
    val cpmPrice: String,
    @JsonProperty("clicprice")
    val clickPrice: String,
    @JsonProperty("dblclicprice")
    val dblClickPrice: String,
    @JsonProperty("leadprice")
    val leadPrice: String,
    @JsonProperty("saleprice")
    val salePrice: String,
    @JsonProperty("downloadprice")
    val downloadPrice: String,
    val status: String,//todo: enum
    @JsonProperty("progdate")
    val progDate: String,//"date time"
    @JsonProperty("progdeb")
    val progDeb: String,//LocalDate
    @JsonProperty("progfin")
    val progFin: String//LocalDate
) {
    val type: TransactionType = when (leadPrice.toIntOrNull()) {
        null, 0 -> TransactionType.Sale
        else -> TransactionType.Lead
    }
}