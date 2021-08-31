package net.besttoolbars.awin

import net.besttoolbars.awin.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class AwinApiTest {
    private val mockWebServer by lazy { MockWebServer() }
    private val mockedAwinApi by lazy { AwinApi.provider(mockWebServer.url("/").toString()) }

    @AfterEach
    fun tearDown() = mockWebServer.shutdown()

    @Test
    fun `fetch transactions`() {
        val response = """
            [
              {
                "id": 920699395,
                "url": "https://my-site.com/",
                "advertiserId": 17874,
                "publisherId": 77777,
                "commissionSharingPublisherId": null,
                "commissionSharingSelectedRatePublisherId": null,
                "campaign": null,
                "siteName": "https://my-site.com/",
                "commissionStatus": "pending",
                "commissionAmount": {
                  "amount": 40.47,
                  "currency": "BRL"
                },
                "saleAmount": {
                  "amount": 1349.00,
                  "currency": "BRL"
                },
                "ipHash": "-8103813414857796536",
                "customerCountry": "BR",
                "clickRefs": {
                  "clickRef": "60e7eee589907f4a24a7c0dc"
                },
                "clickDate": "2021-07-09T06:38:00",
                "transactionDate": "2021-07-09T06:46:00",
                "validationDate": null,
                "type": "Commission group transaction",
                "declineReason": null,
                "voucherCodeUsed": false,
                "voucherCode": null,
                "lapseTime": 458,
                "amended": false,
                "amendReason": null,
                "oldSaleAmount": null,
                "oldCommissionAmount": null,
                "clickDevice": "Windows",
                "transactionDevice": "Windows",
                "customerAcquisition": null,
                "publisherUrl": "https://my-site.com/",
                "advertiserCountry": "BR",
                "orderRef": null,
                "customParameters": null,
                "transactionParts": [
                  {
                    "commissionGroupId": 297335,
                    "amount": 1349.00,
                    "commissionAmount": 40.47,
                    "advertiserCost": null,
                    "commissionGroupCode": "1",
                    "commissionGroupName": "Eletrônicos TV - telas",
                    "trackedParts": [
                      {
                        "code": "1",
                        "amount": 1349.00,
                        "currency": "BRL"
                      }
                    ]
                  }
                ],
                "paidToPublisher": false,
                "paymentId": 0,
                "transactionQueryId": 0,
                "originalSaleAmount": null,
                "advertiserCost": {
                  "amount": null,
                  "currency": null
                },
                "basketProducts": [
                  {
                    "productId": "50005231",
                    "productName": "smart tv led 32 tcl 32s6500s android hdr controle com comando de voz micro dimming google assistant hdmi e usb",
                    "unitPrice": 1349.00,
                    "quantity": 1,
                    "skuCode": "50005231",
                    "commissionGroupCode": "1",
                    "category": "tv e video"
                  }
                ]
              }
            ]
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(response))

        val expected = listOf(
            AwinTransaction(
                id = 920699395,
                url = "https://my-site.com/",
                advertiserId = 17874,
                publisherId = 77777,
                commissionSharingPublisherId = null,
                commissionSharingSelectedRatePublisherId = null,
                campaign = null,
                siteName = "https://my-site.com/",
                commissionStatus = AwinTransactionStatus.PENDING,
                commissionAmount = AwinTransaction.Amount(amount = 40.47, currency = "BRL"),
                saleAmount = AwinTransaction.Amount(amount = 1349.0, currency = "BRL"),
                ipHash = "-8103813414857796536",
                customerCountry = "BR",
                clickRefs = AwinTransaction.ClickRefs(clickRef = "60e7eee589907f4a24a7c0dc"),
                clickDate = LocalDateTime.parse("2021-07-09T06:38:00"),
                transactionDate = LocalDateTime.parse("2021-07-09T06:46:00"),
                validationDate = null,
                type = "Commission group transaction",
                declineReason = null,
                voucherCodeUsed = false,
                voucherCode = null,
                lapseTime = 458,
                amended = false,
                amendReason = null,
                oldSaleAmount = null,
                oldCommissionAmount = null,
                clickDevice = "Windows",
                transactionDevice = "Windows",
                publisherUrl = "https://my-site.com/",
                advertiserCountry = "BR",
                orderRef = null,
                customParameters = null,
                transactionParts = listOf(
                    AwinTransaction.Part(
                        commissionGroupId = 297335,
                        amount = 1349.0,
                        commissionAmount = 40.47,
                        advertiserCost = null,
                        commissionGroupCode = "1",
                        commissionGroupName = "Eletrônicos TV - telas"
                    )
                ),
                paidToPublisher = false,
                paymentId = 0,
                transactionQueryId = 0,
                originalSaleAmount = null,
                basketProducts = listOf(
                    AwinTransaction.BasketProduct(
                        productId = "50005231",
                        productName = "smart tv led 32 tcl 32s6500s android hdr controle com comando de voz micro dimming google assistant hdmi e usb",
                        unitPrice = 1349.0,
                        quantity = 1,
                        skuCode = "50005231",
                        commissionGroupCode = "1",
                        category = "tv e video"
                    )
                )
            )
        )

        val actual = mockedAwinApi.transactions(77777, "", LocalDateTime.now(), LocalDateTime.now()).get()
        Assertions.assertEquals(expected, actual)
    }
}