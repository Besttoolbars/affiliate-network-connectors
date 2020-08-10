package net.besttoolbars.lomadee.response

data class CouponResponse (
    val requestInfo: RequestInfo,
    val pagination: Pagination,
    val coupons: List<Coupon>
)

data class CouponStoreResponse (
    val requestInfo: RequestInfo,
    val coupons: List<CouponStore>
)

data class CouponCategoryResponse (
    val requestInfo: RequestInfo,
    val coupons: List<CouponCategory>
)

data class Coupon (
    val id: Long,
    val description: String,
    val code: String,
    val discount: Long,
    val store: CouponStore,
    val category: CouponCategory,
    val vigency: String,
    val link: String
)

data class CouponCategory (
    val id: Long,
    val name: String
)

data class CouponStore (
    val id: Long,
    val name: String,
    val image: String,
    val link: String
)

data class Pagination (
    val page: Long,
    val size: Long,
    val totalSize: Long,
    val totalPage: Long
)

data class RequestInfo (
    val status: String,
    val message: String
)
