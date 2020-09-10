package net.besttoolbars.cj

import okhttp3.OkHttpClient
import java.time.Duration

fun provideCjRestApiRateLimitInterceptor(requestPerMinute: Long = 25): RateLimitInterceptor {
    return RateLimitInterceptor.of(requestPerMinute, Duration.ofMinutes(1))
}

fun provideCjRestApiHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(provideCjRestApiRateLimitInterceptor())
        .build()
}