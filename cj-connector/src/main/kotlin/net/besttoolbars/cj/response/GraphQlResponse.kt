package net.besttoolbars.cj.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class CjGraphQlResponse<T>(
    val data: T?,
    val errors: List<CjResponseError> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjResponseError (
    val message: String?,
    val locations : List<CjResponseErrorLocation>? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CjResponseErrorLocation (
    val line: Int,
    val column : Int
)