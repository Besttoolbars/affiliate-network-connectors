package net.besttoolbars.connectors.shared

import com.google.common.util.concurrent.RateLimiter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.time.Duration

class RateLimitInterceptor(permitsPerSecond: Double) : Interceptor {
    private val rateLimiter = RateLimiter.create(permitsPerSecond)
    override fun intercept(chain: Interceptor.Chain): Response {
        return rateLimiter.acquire { chain.proceed(chain.request()) }
    }

    companion object {
        fun of(config: RateLimitConfig): RateLimitInterceptor = RateLimitInterceptor(config.permitsPerSecond())

        fun<T> RateLimiter.acquire(permits: Int = 1, block: () -> T): T {
            this.acquire(permits)
            return block()
        }
    }
}

data class RateLimitConfig(
    val requestsForPeriod: Long,
    val period: Duration
) {
    fun permitsPerSecond(): Double = requestsForPeriod.toDouble() / period.toMillis() * 1000
}

fun provideHttpClientWithRateLimit(config: RateLimitConfig, client: OkHttpClient? = null): OkHttpClient {
    client?.let {
        if (client.interceptors().any { it is RateLimitInterceptor })
            return client
    }
    val interceptor = RateLimitInterceptor.of(config)
    val builder = client?.newBuilder() ?: OkHttpClient.Builder()
    return builder.addInterceptor(interceptor).build()
}