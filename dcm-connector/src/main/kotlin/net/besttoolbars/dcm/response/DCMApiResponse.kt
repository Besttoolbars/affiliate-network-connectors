package net.besttoolbars.dcm.response

data class DCMApiResponse<T>(
    val request: Any,
    val response: DCMResponse<T>
)

data class DCMResponse<T>(
    val status: Int,
    val httpStatus: Int,
    val data: T,
    val errors: List<Any>?, // todo find
    val errorMessage: String?
)