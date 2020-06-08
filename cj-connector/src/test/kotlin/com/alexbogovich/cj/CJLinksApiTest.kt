package com.alexbogovich.cj

import com.alexbogovich.cj.response.CjLink
import com.alexbogovich.cj.response.CjLinks
import com.alexbogovich.cj.response.CjLinksResponse
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException


internal class CJLinksApiTest {

    private val mockWebServer = MockWebServer()
    private val mockedApi = CJLinksApi.provider(mockWebServer.url("/").toString())

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `fetch links`() {
        val xml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <cj-api>
                <links total-matched="6946" records-returned="20" page-number="1">
                    <link>
                        <advertiser-id>34</advertiser-id>
                        <advertiser-name>ABC</advertiser-name>
                        <category>Gourmet</category>
                        <click-commission>0.0</click-commission>
                        <creative-height>0</creative-height>
                        <creative-width>0</creative-width>
                        <language>English</language>
                        <lead-commission></lead-commission>
                        <link-code-html>&lt;a href="http://some.url"&gt;JOIN THE&lt;/a&gt;
                        </link-code-html>
                        <link-code-javascript>some js code;</link-code-javascript>
                        <description>ABC description</description>
                        <destination>https://www.abc.com</destination>
                        <link-id>13</link-id>
                        <link-name>ABC link name</link-name>
                        <link-type>Text Link</link-type>
                        <performance-incentive>false</performance-incentive>
                        <promotion-end-date></promotion-end-date>
                        <promotion-start-date></promotion-start-date>
                        <promotion-type>N/A</promotion-type>
                        <coupon-code></coupon-code>
                        <relationship-status>joined</relationship-status>
                        <sale-commission>5.00%</sale-commission>
                        <seven-day-epc>N/A</seven-day-epc>
                        <three-month-epc>0.00</three-month-epc>
                        <clickUrl>http://www.suppose-to-be-affilaite-link.com</clickUrl>
                    </link>
                </links>
            </cj-api>
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(xml))
        val result = mockedApi.links("", "", 1, 20, "").get()
        val except = CjLinksResponse(
            CjLinks(
                total = 6946,
                recordsReturned = 20,
                page = 1,
                link = listOf(
                    CjLink(
                        advertiserId = 34,
                        advertiserName = "ABC",
                        category = "Gourmet",
                        clickCommission = 0f,
                        creativeHeight = 0,
                        creativeWidth = 0,
                        language = "English",
                        leadCommission = null,
                        linkCodeHtml = "<a href=\"http://some.url\">JOIN THE</a>",
                        linkCodeJavascript = "some js code;",
                        description = "ABC description",
                        destination = "https://www.abc.com",
                        linkId = 13,
                        linkName = "ABC link name",
                        linkType = "Text Link",
                        performanceIncentive = false,
                        promotionEndDate = null,
                        promotionStartDate = null,
                        promotionType = "N/A",
                        couponCode = null,
                        relationshipStatus = "joined",
                        saleCommission = "5.00%",
                        sevenDayEpc = null,
                        threeMonthEpc = 0f,
                        clickUrl = "http://www.suppose-to-be-affilaite-link.com"
                    )
                )
            )
        )

        Assertions.assertEquals(result, except)
    }
}
