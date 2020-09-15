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
      validationStatus 
      advertiserId 
      advertiserName 
      clickReferringURL
      coupon 
      orderDiscountUsd 
      saleAmountUsd 
      pubCommissionAmountUsd 
      orderId 
      postingDate 
      shopperId 
      source
      items { 
        itemListId 
        commissionItemId
        quantity
        discountUsd
        perItemSaleAmountUsd
        totalCommissionUsd
      }
    }
  }
}
        """.trimIndent()
    }
}