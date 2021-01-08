package net.besttoolbars.cj.graphql

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object CommissionGraphQlQuery {
    fun publisherCommissions(
        publisher: String,
        from: LocalDateTime,
        to: LocalDateTime = LocalDateTime.now(),
        sinceCommissionId: String? = null
    ): String {
        val dateFromFormatted = from.atZone(ZoneId.of("Z")).format(DateTimeFormatter.ISO_DATE_TIME)
        val dateToFormatted = to.atZone(ZoneId.of("Z")).format(DateTimeFormatter.ISO_DATE_TIME)
        return """
query{ 
  publisherCommissions(
    forPublishers: ["$publisher"],
    sincePostingDate:"$dateFromFormatted",
    beforePostingDate:"$dateToFormatted",
    sinceCommissionId: $sinceCommissionId
  ) {
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
}