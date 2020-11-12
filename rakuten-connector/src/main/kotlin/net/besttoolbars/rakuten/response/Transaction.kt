package net.besttoolbars.rakuten.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "result")
data class RakutenTransactionResponse(
    /**
     * The unique ID associated with the Transaction
     */
    @JacksonXmlProperty(localName = "etransaction_id")
    val etransactionId: String? = null,

    /**
     * The unique ID associated with the Advertiser
     */
    @JacksonXmlProperty(localName = "advertiser_id")
    val advertiserId: String? = null,

    /**
     * Specific Publisher ID
     */
    @JacksonXmlProperty(localName = "sid")
    val sid: String? = null,

    /**
     * The Advertiser’s ID for the order
     */
    @JacksonXmlProperty(localName = "order_id")
    val orderId: String? = null,

    /**
     * The Offer ID associated with the offer
     * that the transaction was commissioned on
     */
    @JacksonXmlProperty(localName = "member_id")
    val memberId: String? = null,

    /**
     * SKU for the Item
     */
    @JacksonXmlProperty(localName = "sku_number")
    val skuNumber: String? = null,

    /**
     * The amount that consumer paid for this item in the Order
     */
    @JacksonXmlProperty(localName = "sale_amount")
    val saleAmount: String? = null,

    /**
     * The quantity of the Items purchased
     */
    @JacksonXmlProperty(localName = "quantity")
    val quantity: String? = null,

    /**
     * The total base commission generated by this transaction
     */
    @JacksonXmlProperty(localName = "commissions")
    val commissions: String? = null,

    /**
     * The date and time when the Order was placed on the Advertisers site
     */
    @JacksonXmlProperty(localName = "process_date")
    val processDate: String? = null,

    /**
     * The date and time when this transaction was processed by Rakuten Marketing
     */
    @JacksonXmlProperty(localName = "transaction_date")
    val transactionDate: String? = null,

    /**
     * The Transaction is Realtime or Batch
     */
    @JacksonXmlProperty(localName = "transaction_type")
    val transactionType: String? = null,

    /**
     * The Product Name of the item purchased or returned
     */
    @JacksonXmlProperty(localName = "product_name")
    val productName: String? = null,

    /**
     * Click that the transaction originated from
     */
    @JacksonXmlProperty(localName = "u1")
    val u1: String? = null,

    /**
     * The currency of the sale amount for the transaction
     */
    @JacksonXmlProperty(localName = "currency")
    val currency: String? = null,

    /**
     * The reported entity is an Event or Transaction
     */
    @JacksonXmlProperty(localName = "is_event")
    val isEvent: String? = null
)