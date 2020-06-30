package net.besttoolbars.rakuten

data class RakutenRequestException(val statusCode: Int, val body: String) :
    RuntimeException("Bad response with code $statusCode. Body: $body")
