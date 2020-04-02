package com.alexbogovich.impactradius

data class ImpactRadiusRequestException(val statusCode: Int, val body: String) :
    RuntimeException("Bad response with code $statusCode. Body: $body")
