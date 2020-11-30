package net.besttoolbars.cj

import net.besttoolbars.cj.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CJAdvertiserApiTest {
    private val mockWebServer = MockWebServer()
    private val mockedApi =
        CJAdvertiserApi.provider(mockWebServer.url("/").toString())

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch advertisers`() {
        val xml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <cj-api>
                <advertisers total-matched="596" records-returned="20" page-number="1">
                    <advertiser>
                        <advertiser-id>123</advertiser-id>
                        <account-status>Active</account-status>
                        <seven-day-epc>12.33</seven-day-epc>
                        <three-month-epc>10.94</three-month-epc>
                        <language>en</language>
                        <advertiser-name>Test advertiser one</advertiser-name>
                        <program-url>https://www.test-advertiser-one.com</program-url>
                        <relationship-status>joined</relationship-status>
                        <mobile-tracking-certified>true</mobile-tracking-certified>
                        <cookieless-tracking-enabled>true</cookieless-tracking-enabled>
                        <network-rank>4</network-rank>
                        <primary-category>
                            <parent>Computer &amp; Electronics</parent>
                            <child>Computer SW</child>
                        </primary-category>
                        <performance-incentives>false</performance-incentives>
                        <actions>
                            <action>
                                <name>Test Purchase</name>
                                <type>advanced sale</type>
                                <id>380</id>
                                <commission>
                                    <itemlist name="ALL PRODUCTS" id="37514">25.00%</itemlist>
                                    <default>25.00%</default>
                                </commission>
                            </action>
                        </actions>
                        <link-types>
                            <link-type>Text Link</link-type>
                            <link-type>Banner</link-type>
                            <link-type>OtherDeepLink</link-type>
                            <link-type>Test Link</link-type>
                            <link-type>DeepLink</link-type>
                            <link-type>AutoMoneyDeepLink</link-type>
                        </link-types>
                    </advertiser>
                    <advertiser>
                        <advertiser-id>56</advertiser-id>
                        <account-status>Active</account-status>
                        <seven-day-epc>12.48</seven-day-epc>
                        <three-month-epc>8.50</three-month-epc>
                        <language>en</language>
                        <advertiser-name>Test advertiser two</advertiser-name>
                        <program-url>https://www.test-advertiser-two.com/</program-url>
                        <relationship-status>joined</relationship-status>
                        <mobile-tracking-certified>false</mobile-tracking-certified>
                        <cookieless-tracking-enabled>false</cookieless-tracking-enabled>
                        <network-rank>1</network-rank>
                        <primary-category>
                            <parent>Food &amp; Drinks</parent>
                            <child>Gourmet</child>
                        </primary-category>
                        <performance-incentives>false</performance-incentives>
                        <actions>
                            <action>
                                <name>Test Purchase v2</name>
                                <type>advanced sale</type>
                                <id>87</id>
                                <commission>
                                    <default>5.00%</default>
                                </commission>
                            </action>
                        </actions>
                        <link-types>
                            <link-type>Text Link</link-type>
                            <link-type>Banner</link-type>
                            <link-type>OtherDeepLink</link-type>
                            <link-type>Test Link</link-type>
                            <link-type>DeepLink</link-type>
                            <link-type>AutoMoneyDeepLink</link-type>
                        </link-types>
                    </advertiser>
                </advertisers>
            </cj-api>
            
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(xml))
        val result = mockedApi.advertisers("", "", 1, 20).get()

        val expected = CjAdvertisersResponse(
            advertisers = CjAdvertisers(
                total = 596,
                page = 1,
                perPage = 20,
                advertiser = listOf(
                    CjAdvertiser(
                        advertiserId = 123,
                        accountStatus = "Active",
                        sevenDayEpc = 12.33f,
                        threeMonthEpc = 10.94f,
                        language = "en",
                        advertiserName = "Test advertiser one",
                        programUrl = "https://www.test-advertiser-one.com",
                        relationshipStatus = "joined",
                        mobileTrackingCertified = true,
                        cookielessTrackingEnabled = true,
                        networkRank = "4",
                        primaryCategory = CjAdvertiserPrimaryCategory(
                            parent = "Computer & Electronics",
                            child = "Computer SW"
                        ),
                        performanceIncentives = false,
                        actions = listOf(
                            CjAdvertiserAction(
                                "Test Purchase",
                                "advanced sale",
                                380,
                                CjActionCommission(
                                    listOf(
                                        CjActionCommissionItem(
                                            name = "ALL PRODUCTS",
                                            id = "37514",
                                            text = "25.00%"
                                        )
                                    ),
                                    default = CjActionCommissionDefault("25.00%")
                                )
                            )
                        ),
                        linkTypes = listOf(
                            "Text Link",
                            "Banner",
                            "OtherDeepLink",
                            "Test Link",
                            "DeepLink",
                            "AutoMoneyDeepLink"
                        ),
                        mobileSupported = null
                    ),
                    CjAdvertiser(
                        advertiserId = 56,
                        accountStatus = "Active",
                        sevenDayEpc = 12.48f,
                        threeMonthEpc = 8.50f,
                        language = "en",
                        advertiserName = "Test advertiser two",
                        programUrl = "https://www.test-advertiser-two.com/",
                        relationshipStatus = "joined",
                        mobileTrackingCertified = false,
                        cookielessTrackingEnabled = false,
                        networkRank = "1",
                        primaryCategory = CjAdvertiserPrimaryCategory(
                            parent = "Food & Drinks",
                            child = "Gourmet"
                        ),
                        performanceIncentives = false,
                        actions = listOf(
                            CjAdvertiserAction(
                                "Test Purchase v2",
                                "advanced sale",
                                87,
                                CjActionCommission(
                                    listOf(),
                                    CjActionCommissionDefault("5.00%")
                                )
                            )
                        ),
                        linkTypes = listOf(
                            "Text Link",
                            "Banner",
                            "OtherDeepLink",
                            "Test Link",
                            "DeepLink",
                            "AutoMoneyDeepLink"
                        ),
                        mobileSupported = null
                    )
                )
            )
        )

        Assertions.assertEquals(expected, result)
    }
}
