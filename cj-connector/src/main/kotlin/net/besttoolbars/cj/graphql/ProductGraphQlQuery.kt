package net.besttoolbars.cj.graphql

object ProductGraphQlQuery {
    fun shoppingProducts(
        companyId: String,
        websiteId: String,
        limit: Int = 1000,
        offset: Int = 0,
        adIds: Set<String>? = null,
        googleProductCategoryIds: Set<String>? = null
    ): String {
        return """
query {
  shoppingProducts(
    companyId: $companyId,
    limit: $limit,
    offset: $offset,
    adIds: $adIds,
    googleProductCategoryIds: $googleProductCategoryIds
  ) {
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
      linkCode(pid: $websiteId) {
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
}