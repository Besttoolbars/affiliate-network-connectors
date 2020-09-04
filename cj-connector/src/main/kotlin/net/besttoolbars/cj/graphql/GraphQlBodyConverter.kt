package net.besttoolbars.cj.graphql

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.RequestBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.Body
import java.lang.reflect.Type

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class GraphQl

internal class GraphQlBodyConverter : Converter.Factory() {
    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return if (!hasGraphQlBody(parameterAnnotations)) {
            return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
        } else Converter<Any, RequestBody> { value ->
            val body = when (value) {
                is String -> value
                else -> jacksonObjectMapper().writeValueAsString(value)
            }
            RequestBody.create(null, body)
        }
    }

    private fun hasGraphQlBody(annotations: Array<Annotation>): Boolean {
        val isBody = annotations.any{ annotation -> annotation is Body }
        val isGraphQl = annotations.any{ annotation -> annotation is GraphQl }
        return isBody && isGraphQl
    }
}