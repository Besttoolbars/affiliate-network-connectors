package net.besttoolbars.cj

import okhttp3.OkHttpClient
import java.time.Duration

fun provideCjRestApiRateLimitInterceptor(requestPerMinute: Long = 25): RateLimitInterceptor {
    return RateLimitInterceptor.of(requestPerMinute, Duration.ofMinutes(1))
}

fun provideCjRestApiHttpClient(
    client: OkHttpClient? = null,
    rateLimitInterceptor: RateLimitInterceptor = provideCjRestApiRateLimitInterceptor()
): OkHttpClient {
    client?.let {
        if (client.interceptors().any { it is RateLimitInterceptor })
            return client
    }
    val builder = client?.newBuilder() ?: OkHttpClient.Builder()
    return builder.addInterceptor(rateLimitInterceptor).build()
}