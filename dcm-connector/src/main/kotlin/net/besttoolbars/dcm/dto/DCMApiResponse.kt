package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMErrorsDeserializer

data class DCMApiResponse<T>(
    val request: Any? = null,
    val response: DCMResponse<T>
)

data class DCMResponse<T>(
    val status: Int? = null,
    val httpStatus: Int? = null,
    val data: T? = null,

    @JsonDeserialize(using = DCMErrorsDeserializer::class)
    val errors: List<DCMError> = emptyList(),
    val errorMessage: String? = null
)