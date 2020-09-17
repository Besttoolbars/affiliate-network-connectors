package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.LocalDateTimeDeserializer
import java.time.LocalDateTime

data class DCMOfferUrl(
    val id: Int,

    @JsonProperty("offer_id")
    val offerId: Int,

    val name: String,

    @JsonProperty("preview_url")
    val previewUrl: String,

    val status: OfferStatus,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val created: LocalDateTime,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val modified: LocalDateTime? = null
)