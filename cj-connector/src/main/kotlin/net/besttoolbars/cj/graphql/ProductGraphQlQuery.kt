package net.besttoolbars.cj.graphql

import net.besttoolbars.connectors.shared.graphql.gqlParamsBuilder

object ProductGraphQlQuery {
    fun shoppingProducts(query: QueryParams): String {
        return """
query {
  shoppingProducts(${query.toShoppingParams()}) {
    totalCount
    count
    limit
    resultList {
      id
      gtin
      mpn
      link
      title
      brand
      catalogId
      imageLink
      description
      productType
      
      adId
      adult
      advertiserCountry
      advertiserId
      advertiserName
      ageGroup
      availability
      availabilityDate
      condition
      energyEfficiencyClass
      energyEfficiencyClassMax
      energyEfficiencyClassMin
      identifierExists

      additionalImageLink
      salePriceEffectiveDateEnd
      salePriceEffectiveDateStart
      targetCountry
      unitPricingMeasure
      unitPricingBaseMeasure
      isBundle
      itemGroupId
      itemListId
      itemListName
      serviceableAreas
      
      size
      sizeType
      sizeSystem
      color
      material
      multipack
      pattern
      gender
      
      sourceFeedType
      lastUpdated
      expirationDate
      
      maximumHandlingTime
      minimumHandlingTime
      mobileLink
      
      price {
        amount
        currency
      }
      salePrice {
        amount
        currency
      }
      linkCode(${query.toLinkCodeParams()}) {
        clickUrl
      }
      loyaltyPoints {
        name
        points
        ratio
      }
      googleProductCategory {
        id
        name
      }
      installment {
        amount {
          amount
          currency
        }
        months
      }
      shipping {
        country
        height
        length
        width
        weight
        postalCode
        price {
          amount
          currency
        }
        region
        service
      }
      tax {
        countryCode
        locationGroupName
        locationId
        postalCode
        rate
        region
        taxShip
      }
    }
  }
}
    """.trimIndent()
    }
    
    fun shoppingProducts(
        companyId: String,
        websiteId: String,
        limit: Int = 1000,
        offset: Int = 0,
        offerIds: Set<String>? = null,
        advertiserIds: Set<String>? = null,
        serviceableAreas: Set<String>? = null,
        googleProductCategoryIds: Set<String>? = null,
        advertiserStatus: QueryParams.AdvertiserStatus = QueryParams.AdvertiserStatus.JOINED,
        keywords: Set<String>? = null,
        availability: QueryParams.Availability? = null,
        currency: String? = null,
        lowPrice: Double? = null,
        highPrice: Double? = null
    ): String {
        val queryParams = QueryParams(
            companyId = companyId,
            websiteId = websiteId,
            limit = limit,
            offset = offset,
            offerIds = offerIds,
            advertiserIds = advertiserIds,
            serviceableAreas = serviceableAreas,
            googleProductCategoryIds = googleProductCategoryIds,
            advertiserStatus = advertiserStatus,
            keywords = keywords,
            availability = availability,
            currency = currency,
            lowPrice = lowPrice,
            highPrice = highPrice
        )
        return shoppingProducts(queryParams)
    }

    data class QueryParams(
        val companyId: String,
        val websiteId: String,
        val limit: Int = 1000,
        val offset: Int = 0,
        val offerIds: Set<String>? = null,
        val advertiserIds: Set<String>? = null,
        val serviceableAreas: Set<String>? = null,
        val googleProductCategoryIds: Set<String>? = null,
        val advertiserStatus: AdvertiserStatus = AdvertiserStatus.JOINED,
        val keywords: Set<String>? = null,
        val availability: Availability? = null,
        val currency: String? = null,
        val lowPrice: Double? = null,
        val highPrice: Double? = null
    ) {
        fun toShoppingParams(): String =
            gqlParamsBuilder {
                "companyId" setNotNull companyId
                "limit" set limit
                "offset" set offset
                "currency" set currency?.let { "\"$it\"" }
                "lowPrice" set lowPrice
                "highPrice" set highPrice
                "adIds" setNotEmpty offerIds
                "availability" set availability
                "partnerStatus" set advertiserStatus
                "partnerIds" setNotEmpty advertiserIds
                "keywords" setNotEmpty keywords?.map { "\"$it\"" }
                "googleProductCategoryIds" setNotEmpty googleProductCategoryIds
            }

        fun toLinkCodeParams(): String = gqlParamsBuilder { "pid" setNotNull websiteId }

        enum class AdvertiserStatus {
            JOINED,
            NOT_JOINED
        }

        enum class Availability {
            IN_STOCK,
            OUT_OF_STOCK,
            PREORDER
        }
    }
}