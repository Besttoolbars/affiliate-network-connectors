package net.besttoolbars.cj.graphql

import net.besttoolbars.connectors.shared.graphql.gqlParamsBuilder
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

object CommissionGraphQlQuery {
    @Deprecated("Use publisherCommissions(query) instead", ReplaceWith("publisherCommissions(query)"))
    fun publisherCommissions(
        publisher: String,
        from: LocalDateTime,
        to: LocalDateTime = LocalDateTime.now(),
        sinceCommissionId: String? = null
    ): String {
        val query = Query(
            forPublishers = setOf(publisher),
            sinceCommissionId = sinceCommissionId,
            sincePostingDate = from.atZone(ZoneId.of("Z")),
            beforePostingDate = to.atZone(ZoneId.of("Z"))
        )
        return publisherCommissions(query)
    }

    fun publisherCommissions(query: Query): String {
        return """
query { 
  publisherCommissions(${query.toQueryString()}) {
    count 
    payloadComplete 
    maxCommissionId 
    records {
      aid
      clickDate
      eventDate
      commissionId 
      actionStatus
      validationStatus 
      advertiserId 
      advertiserName 
      clickReferringURL
      coupon 
      orderDiscountPubCurrency
      orderDiscountUsd 
      saleAmountPubCurrency
      saleAmountUsd 
      pubCommissionAmountPubCurrency
      pubCommissionAmountUsd 
      orderId 
      postingDate 
      shopperId 
      source
      items { 
        itemListId 
        commissionItemId
        quantity
        discountPubCurrency
        discountUsd
        perItemSaleAmountPubCurrency
        perItemSaleAmountUsd
        totalCommissionPubCurrency
        totalCommissionUsd
      }
    }
  }
}
        """.trimIndent()
    }

    class Query(
        var forPublishers: Set<String>,
        var sincePostingDate: ZonedDateTime? = null,
        var beforePostingDate: ZonedDateTime? = null,
        var sinceEventDate: ZonedDateTime? = null,
        var beforeEventDate: ZonedDateTime? = null,
        var sinceLockingDate: ZonedDateTime? = null,
        var beforeLockingDate: ZonedDateTime? = null,
        var sinceCommissionId: String? = null,
        var commissionIds: Set<String>? = null,
        var advertiserIds: Set<String>? = null,
        var adIds: Set<String>? = null,
        var websiteIds: Set<String>? = null,
        var actionStatuses: Set<String>? = null,
        var actionTypes: Set<String>? = null,
        var lockingMethods: Set<String>? = null,
        var validationStatuses: Set<String>? = null
    ) {
        fun toQueryString(): String =
            gqlParamsBuilder {
                "forPublishers" setNotNullable forPublishers
                "sincePostingDate" set sincePostingDate
                "beforePostingDate" set beforePostingDate
                "sinceEventDate" set sinceEventDate
                "beforeEventDate" set beforeEventDate
                "sinceLockingDate" set sinceLockingDate
                "beforeLockingDate" set beforeLockingDate
                "sinceCommissionId" set sinceCommissionId
                "commissionIds" set commissionIds
                "advertiserIds" set advertiserIds
                "adIds" set adIds
                "websiteIds" set websiteIds
                "actionStatuses" set actionStatuses
                "actionTypes" set actionTypes
                "lockingMethods" set lockingMethods
                "validationStatuses" set validationStatuses
            }
    }
}