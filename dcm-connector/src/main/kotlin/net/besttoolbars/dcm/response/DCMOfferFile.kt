package net.besttoolbars.dcm.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.converters.DCMBooleanDeserializer
import net.besttoolbars.dcm.converters.LocalDateTimeDeserializer
import java.time.LocalDateTime

data class DCMOfferFile(
    val id: Int,

    @JsonProperty("account_id")
    val accountId: Int? = null,

    val code: String? = null,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val created: LocalDateTime,

    val display: String,

    val filename: String,

    @JsonProperty("flash_vars")
    val flashVars: String? = null,

    val height: Int? = null,

    val `interface`: Interface,

    @JsonProperty("is_private")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val isPrivate: Boolean,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val modified: LocalDateTime? = null,

    @JsonProperty("offer_id")
    val offerId: Int,

    val size: Int,

    val status: FileStatus,

    val thumbnail: String? = null,

    val type: FileType,

    val url: String? = null,

    val width: Int? = null
)

enum class Interface{
    NETWORK,
    AFFILIATE,
    ADVERTISER
}

enum class FileStatus{
    ACTIVE,
    PENDING,
    DETECTED
}

enum class FileType{
    @JsonProperty("file")
    FILE,

    @JsonProperty("image banner")
    IMAGE_BANNER,

    @JsonProperty("flash banner")
    FLASH_BANNER,

    @JsonProperty("email creative")
    EMAIL_CREATIVE,

    @JsonProperty("offer thumbnail")
    OFFER_THUMBNAIL,

    @JsonProperty("text ad")
    TEXT_AD,

    @JsonProperty("html ad")
    HTML_AD,

    @JsonProperty("hidden")
    HIDDEN,

    @JsonProperty("xml feed")
    XML_FEED
}