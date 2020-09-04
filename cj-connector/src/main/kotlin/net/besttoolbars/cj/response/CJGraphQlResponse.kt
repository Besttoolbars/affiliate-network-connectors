package net.besttoolbars.cj.response

data class CJGraphQlResponse<T>(
    val data: T?,
    val errors: List<Any> = emptyList()
)