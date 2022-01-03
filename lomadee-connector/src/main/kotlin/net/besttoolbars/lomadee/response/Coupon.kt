package net.besttoolbars.lomadee.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.lomadee.deserializer.LomadeeVigencyLocalDateTimeDeserializer
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class CouponResponse(
    val requestInfo: RequestInfo,
    val pagination: Pagination?,
    val coupons: List<Coupon>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CouponStoreResponse(
    val requestInfo: RequestInfo,
    val stores: List<CouponStore>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CouponCategoryResponse(
    val requestInfo: RequestInfo,
    val categories: List<CouponCategory>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Coupon(
    val id: Long,
    val description: String,
    val code: String,
    val discount: Double,
    val store: CouponStore,
    val category: CouponCategory,
    @JsonDeserialize(using = LomadeeVigencyLocalDateTimeDeserializer::class)
    val vigency: LocalDateTime,
    val link: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CouponCategory(
    val id: Long,
    val name: String,
    val link: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CouponStore(
    val id: Long?,
    val name: String?,
    val image: String?,
    val link: String?
)
