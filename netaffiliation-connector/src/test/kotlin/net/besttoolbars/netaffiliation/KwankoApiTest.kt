package net.besttoolbars.netaffiliation

import net.besttoolbars.netaffiliation.response.PromoCode
import net.besttoolbars.netaffiliation.response.PromoCodesChannel
import net.besttoolbars.netaffiliation.response.RssImage
import net.besttoolbars.netaffiliation.response.RssPromoCodes
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class KwankoApiTest {
    private val mockWebServer = MockWebServer()
    private val kwankoApi = KwankoApi.provider(mockWebServer.url("/").toString())

    @Test
    fun `fetch rss promo codes`() {
        val response = """
            <rss version="2.0">
              <channel>
                <title>All promotion codes at the time</title>
                <link>https://www.netaffiliation.com</link>
                <description>Find out the coupon codes and coupons from merchants NetAffiliation specially selected by their attractive offers.</description>
                <langue>nl_NL</langue>
                <pubDate>Fri, 14 Aug 2020 00:51:46 +0000</pubDate>
                <lastBuildDate>Fri, 14 Aug 2020 00:51:46 +0000</lastBuildDate>
                <docs>https://blogs.law.harvard.edu/tech/rss</docs>
                <ttl>43200</ttl>
                <image>
                  <url>https://www.netaffiliation.com/v/images/na/na/logo-netaffiliation.jpg</url>
                  <title>NetAffiliation, affiliation platform</title>
                  <link>https://www.netaffiliation.com</link>
                  <width>100</width>
                  <height>24</height>
                </image>
                <item>
                  <title>NL Hopt : Discount for new customers</title>
                  <link><![CDATA[https://hpz.hopt.nl/?P510C4557526F1B1]]></link>
                  <description><![CDATA[WELCOME5 : Gives -5 € discount for new customers, on orders of 40 € or over. ]]></description>
                  <code><![CDATA[WELCOME5]]></code>
                  <startdate>2020-02-11 14:00:00</startdate>
                  <enddate/>
                  <timezone>UTC</timezone>
                  <idcamp>68677</idcamp>
                  <datemod>2020-02-11 14:25:14</datemod>
                </item>
                <item>
                  <title>NL Hopt : Discount for new customers</title>
                  <link><![CDATA[https://hpz.hopt.nl/?P510C4557526F1B1]]></link>
                  <description><![CDATA[HOPTHELLO5 (Available until 31-12-2020) : 5€ from 100€ without beer dispenser, gift card and available only for new customers.<br />Dates: valid until 31/12/2020]]></description>
                  <code><![CDATA[HOPTHELLO5]]></code>
                  <startdate>2020-08-12 14:00:00</startdate>
                  <enddate>2020-12-31 22:59:59</enddate>
                  <timezone>UTC</timezone>
                  <idcamp>68677</idcamp>
                  <datemod>2020-08-12 14:36:45</datemod>
                </item>
              </channel>
            </rss>
        """.trimIndent()

        val expected = RssPromoCodes(
            channel = PromoCodesChannel(
                title = "All promotion codes at the time",
                link = "https://www.netaffiliation.com",
                description = "Find out the coupon codes and coupons from merchants NetAffiliation specially selected by their attractive offers.",
                item = listOf(
                    PromoCode(
                        link = "https://hpz.hopt.nl/?P510C4557526F1B1",
                        title = "NL Hopt : Discount for new customers",
                        description = "WELCOME5 : Gives -5 € discount for new customers, on orders of 40 € or over.",
                        code = "WELCOME5",
                        startDate = "2020-02-11 14:00:00",
                        endDate = null,
                        timezone = "UTC",
                        idCamp = 68677,
                        dateMod = "2020-02-11 14:25:14"
                    ),
                    PromoCode(
                        link = "https://hpz.hopt.nl/?P510C4557526F1B1",
                        title = "NL Hopt : Discount for new customers",
                        description = "HOPTHELLO5 (Available until 31-12-2020) : 5€ from 100€ without beer dispenser, gift card and available only for new customers.<br />Dates: valid until 31/12/2020",
                        code = "HOPTHELLO5",
                        startDate = "2020-08-12 14:00:00",
                        endDate = "2020-12-31 22:59:59",
                        timezone = "UTC",
                        idCamp = 68677,
                        dateMod = "2020-08-12 14:36:45"
                    )
                ),
                langue = "nl_NL",
                pubDate = "Fri, 14 Aug 2020 00:51:46 +0000",
                lastBuildDate = "Fri, 14 Aug 2020 00:51:46 +0000",
                ttl = 43200,
                image = RssImage(
                    url = "https://www.netaffiliation.com/v/images/na/na/logo-netaffiliation.jpg",
                    link = "https://www.netaffiliation.com",
                    title = "NetAffiliation, affiliation platform",
                    width = 100,
                    height = 24
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val coupons = kwankoApi.rssCoupons("").get()
        Assertions.assertEquals(expected, coupons)
    }
}