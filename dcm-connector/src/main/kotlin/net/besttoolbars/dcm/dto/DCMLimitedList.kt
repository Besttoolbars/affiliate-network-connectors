package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMOfferFileListDataDeserializer
import net.besttoolbars.dcm.deserializers.DCMOfferListDataDeserializer
import net.besttoolbars.dcm.deserializers.DCMOfferUrlListDataDeserializer

data class DCMOfferLimitedList(
    val page: Int,
    val current: Int,
    val count: Int,
    val pageCount: Int,

    @JsonDeserialize(using = DCMOfferListDataDeserializer::class)
    val data: Map<String, DCMOfferListData>
)

data class DCMOfferListData(
    @JsonProperty("Offer")
    val offer: DCMOffer
)

data class DCMOfferUrlLimitedList(
    val page: Int,
    val current: Int,
    val count: Int,
    val pageCount: Int,

    @JsonDeserialize(using = DCMOfferUrlListDataDeserializer::class)
    val data: Map<String, DCMOfferUrlListData>
)

data class DCMOfferUrlListData(
    @JsonProperty("OfferUrl")
    val offerUrl: DCMOfferUrl
)

data class DCMOfferFileLimitedList(
    val page: Int,
    val current: Int,
    val count: Int,
    val pageCount: Int,

    @JsonDeserialize(using = DCMOfferFileListDataDeserializer::class)
    val data: Map<String, DCMOfferFileListData>
)

data class DCMOfferFileListData(
    @JsonProperty("OfferFile")
    val offerFile: DCMOfferFile
)