package net.besttoolbars.cj

import com.google.common.util.concurrent.RateLimiter
import okhttp3.Interceptor
import okhttp3.Response
import java.time.Duration

class RateLimitInterceptor(permitsPerSecond: Double) : Interceptor {
    private val rateLimiter = RateLimiter.create(permitsPerSecond)
    override fun intercept(chain: Interceptor.Chain): Response {
        return rateLimiter.acquire { chain.proceed(chain.request()) }
    }

    companion object {
        fun of(requestsForPeriod: Long, period: Duration): RateLimitInterceptor {
            val permitsPerSecond = requestsForPeriod.toDouble() / period.toMillis() * 1000
            return RateLimitInterceptor(permitsPerSecond)
        }

        fun<T> RateLimiter.acquire(permits: Int = 1, block: () -> T): T {
            this.acquire(permits)
            return block()
        }
    }
}