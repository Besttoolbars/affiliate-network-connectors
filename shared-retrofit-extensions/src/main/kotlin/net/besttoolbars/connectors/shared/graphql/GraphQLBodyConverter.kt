package net.besttoolbars.connectors.shared.graphql

import net.besttoolbars.connectors.shared.provideJsonObjectMapper
import okhttp3.RequestBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.Body
import java.lang.reflect.Type

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class GraphQL

class GraphQLBodyConverter : Converter.Factory() {
    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return if (!hasGraphQlBody(parameterAnnotations)) {
            return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
        } else Converter<Any, RequestBody> { value ->
            val body = when (value) {
                is String -> value
                else -> mapper.writeValueAsString(value)
            }
            RequestBody.create(null, body)
        }
    }

    private fun hasGraphQlBody(annotations: Array<Annotation>): Boolean {
        val isBody = annotations.any{ annotation -> annotation is Body }
        val isGraphQl = annotations.any{ annotation -> annotation is GraphQL }
        return isBody && isGraphQl
    }

    private val mapper by lazy { provideJsonObjectMapper() }
}