package net.besttoolbars.rakuten

import net.besttoolbars.rakuten.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class RakutenApiTest {
    private val mockWebServer by lazy { MockWebServer() }
    private val rakutenApi by lazy { RakutenApi.provider(mockWebServer.url("/").toString()) }

    @AfterEach
    private fun turnOffServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch rakuten coupons`() {
        val body = """
            <couponfeed>
              <TotalMatches>8909</TotalMatches>
              <TotalPages>18</TotalPages>
              <PageNumberRequested>1</PageNumberRequested>
              <link type="TEXT">
                <categories>
                  <category id="6">Beauty &amp; Fragrance</category>
                </categories>
                <promotiontypes>
                  <promotiontype id="9">Gift with Purchase</promotiontype>
                </promotiontypes>
                <offerdescription>Enjoy a Free Soy Face Cleanser and  Kombucha Essence  with Any ${'$'}80 Plus Purchase!</offerdescription>
                <offerstartdate>2022-01-02T08:00Z</offerstartdate>
                <offerenddate>2022-01-10T08:00Z</offerenddate>
                <couponcode>8138182323</couponcode>
                <couponrestriction>On Orders Above ${'$'}80</couponrestriction>
                <clickurl>https://click.linksynergy.com/fs-bin/click?id=z7blP4AIgi8&amp;offerid=785823.240&amp;type=3&amp;subid=0</clickurl>
                <impressionpixel>https://ad.linksynergy.com/fs-bin/show?id=z7blP4AIgi8&amp;bids=785823.240&amp;type=3&amp;subid=0</impressionpixel>
                <advertiserid>44461</advertiserid>
                <advertisername>Fresh US</advertisername>
                <network id="1">US Network</network>
              </link>
            </couponfeed>
        """.trimIndent()

        val expected = RakutenCouponResponse(
            totalMatch = 8909,
            totalPage = 18,
            pageNumberRequested = 1,
            links = listOf(
                RakutenCoupon(
                    type = "TEXT",
                    offerDescription = "Enjoy a Free Soy Face Cleanser and  Kombucha Essence  with Any $80 Plus Purchase!",
                    categories = listOf(RakutenCouponCategory().apply { id = "6"; value = "Beauty & Fragrance" }),
                    promotionTypes = listOf(RakutenCouponPromotionType().apply {
                        id = "9"; value = "Gift with Purchase"
                    }),
                    offerStartDate = ZonedDateTime.of(2022, 1, 2, 8, 0, 0, 0, ZoneId.of("UTC")),
                    offerEndDate = ZonedDateTime.of(2022, 1, 10, 8, 0, 0, 0, ZoneId.of("UTC")),
                    couponCode = "8138182323",
                    couponRestriction = "On Orders Above $80",
                    clickUrl = "https://click.linksynergy.com/fs-bin/click?id=z7blP4AIgi8&offerid=785823.240&type=3&subid=0",
                    impressionPixel = "https://ad.linksynergy.com/fs-bin/show?id=z7blP4AIgi8&bids=785823.240&type=3&subid=0",
                    advertiserId = 44461,
                    advertiserName = "Fresh US",
                    network = RakutenCouponNetwork().apply { id = "1"; value = "US Network" }
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(body))
        val actual = rakutenApi.coupons("", 1).get()
        Assertions.assertEquals(expected, actual)
    }
}