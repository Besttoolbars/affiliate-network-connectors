package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMCategoriesDeserializer

data class DCMOfferCategory(
    @JsonProperty("offer_id")
    val offerId: Int,

    @JsonDeserialize(using = DCMCategoriesDeserializer::class)
    val categories: Map<Int, DCMCategory> = emptyMap()
)

data class DCMCategory(
    val id: Int,
    val name: String

    // todo соорудить мапу?? мало данных
)