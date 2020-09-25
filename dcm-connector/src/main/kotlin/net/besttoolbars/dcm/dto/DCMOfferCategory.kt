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
) {
    val type: DCMCategoryType = DCMCategoryType.getById(id)
}

enum class DCMCategoryType(val id: Int) {
    TRAVEL(2),
    ART_PHOTO_MUSIC(24),
    AUTOMATIVE(26),
    BOOKS_MEDIA(28),
    BUSINESS(30),
    BUYING_SELLING(32),
    CAREERS(34),
    FASHION_ACCESSORIES(36),
    DEPARTMENT_STORES(38),
    ENTERTAINMENT(42),
    FINANCIAL_SERVICES(46),
    FOOD_DRINKS(48),
    GAMES_TOYS(50),
    GIFTS_FLOWERS(52),
    HEALTH_WELLNESS(54),
    HOME_GARDEN(56),
    INSURANCE(58),
    LEGAL(60),
    MARKETING(62),
    NON_PROFIT(66),
    ONLINE_SERVICES(68),
    RECREATION_LEISURE(70),
    SPORTS_FITNESS(72),
    SEASONAL(76),
    CLEANING_SERVICE(82),
    MOMMY_BABY(88),
    ONLINE_EDUCATION(92),
    ELECTRONICS(113),
    BEAUTY(115),

    UNKNOWN(0);

    companion object {
        fun getById(id: Int): DCMCategoryType {
            return DCMCategoryType.values().find { it.id == id } ?: UNKNOWN
        }
    }
}