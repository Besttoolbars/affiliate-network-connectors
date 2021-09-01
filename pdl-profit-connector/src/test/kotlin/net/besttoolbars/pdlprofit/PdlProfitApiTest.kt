package net.besttoolbars.pdlprofit

import net.besttoolbars.pdlprofit.response.PdlProfitConversion
import net.besttoolbars.pdlprofit.response.PdlProfitLeadType
import net.besttoolbars.pdlprofit.response.PdlProfitOffer
import net.besttoolbars.pdlprofit.response.PdlProfitResponse
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

internal class PdlProfitApiTest {
    private val mockWebServer by lazy { MockWebServer() }
    private val mockedPdlProfitApi by lazy { PdlProfitApi.create(mockWebServer.url("/").toString()) }
    private val instantDatetimeFormatter by lazy {
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneOffset.UTC)
    }

    @AfterEach
    fun tearDown() = mockWebServer.shutdown()

    @Test
    fun `fetch offers`() {
        val response = """
            {
                  "status": "success",
                  "meassage": "Offers date",
                  "count": 1,
                  "pages": 1,
                  "currentPage": 1,
                  "data": [
                        {
                          "id": 1147,
                          "name": "Credit7",
                          "country_code": "UA",
                          "image": "https://pdl-profit.com/uploads/2021-02-23/file_6035622851074.png",
                          "credit": "5000",
                          "credit_repeat": "10000",
                          "days": "61",
                          "first_credit": "10000",
                          "second_credit": "10000",
                          "term": "30",
                          "first_credit_percent": "0.01",
                          "first_credit_percent_standard": "1.9",
                          "url": "https:\/\/tds.pdl-profit.com?offerid=22&affid=33",
                          "ecpc": null,
                          "cr": null
                        }
                  ]
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(response))

        val expected = PdlProfitResponse(
            status = "success",
            message = "Offers date",
            count = 1,
            pages = 1,
            currentPage = 1,
            data = listOf(
                PdlProfitOffer(
                    id = 1147,
                    name = "Credit7",
                    image = "https://pdl-profit.com/uploads/2021-02-23/file_6035622851074.png",
                    credit = 5000,
                    creditRepeat = 10000,
                    days = 61,
                    firstCredit = 10000,
                    secondCredit = 10000,
                    term = 30,
                    firstCreditPercent = "0.01",
                    firstCreditPercentStandard = "1.9",
                    url = "https://tds.pdl-profit.com?offerid=22&affid=33",
                    ecpc = null,
                    cr = null,
                    countryCode = "UA"
                )
            )
        )
        val actual = mockedPdlProfitApi.offers("").get()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `fetch conversions`() {
        val response = """
            {
                "status": "success",
                "meassage": "Postbacks data loaded",
                "count": 31,
                "data": [{
                    "name": "MILOAN",
                    "click_unix_date": 1591830304,
                    "postback_unix_date": 1591830569,
                    "s_date": "2020-06-10",
                    "click_date": "10-06-2020 23:05:04",
                    "lead_date": "10-06-2020 23:09:29",
                    "summ": 4.488758025301738,
                    "currency": "USD",
                    "lead_type": "CPL",
                    "sub_id": "59d90h9x9a7g5ec3",
                    "subid2": null,
                    "subid3": null,
                    "utm_source": "947",
                    "utm_medium": null,
                    "utm_campaign": "IOS",
                    "utm_term": null,
                    "utm_adgroup": null,
                    "utm_adposition": null,
                    "utm_creative": null,
                    "utm_device": null,
                    "gclid": null,
                    "country_code": "UA",
                    "ip": "46.136.118.57",
                    "user_detailed_data": "",
                    "transaction_id": "fa09842e-58d2-4d32-9389-669ffd3596f1",
                    "city": "Kiev"
                }]
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(response))

        val expected = PdlProfitResponse(
            status = "success",
            message = "Postbacks data loaded",
            count = 31,
            data = listOf(
                PdlProfitConversion(
                    name = "MILOAN",
                    clickUnixDate = 1591830304,
                    postbackUnixDate = 1591830569,
                    sDate = LocalDate.of(2020, 6, 10),
                    clickDate = instantDatetimeFormatter.parse("10-06-2020 23:05:04", Instant::from),
                    leadDate = instantDatetimeFormatter.parse("10-06-2020 23:09:29", Instant::from),
                    summ = 4.488758025301738,
                    currency = "USD",
                    leadType = PdlProfitLeadType.CPL,
                    subId = "59d90h9x9a7g5ec3",
                    subId2 = null,
                    subId3 = null,
                    utmSource = "947",
                    utmMedium = null,
                    utmCampaign = "IOS",
                    utmTerm = null,
                    utmAdgroup = null,
                    utmAdposition = null,
                    utmCreative = null,
                    utmDevice = null,
                    gclid = null,
                    countryCode = "UA",
                    ip = "46.136.118.57",
                    userDetailedData = "",
                    transactionId = "fa09842e-58d2-4d32-9389-669ffd3596f1",
                    city = "Kiev"
                )
            )
        )
        val actual = mockedPdlProfitApi.conversions("", LocalDate.now(), LocalDate.now()).get()
        Assertions.assertEquals(expected, actual)
    }
}