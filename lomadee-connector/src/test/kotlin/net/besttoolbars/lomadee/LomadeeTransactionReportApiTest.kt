package net.besttoolbars.lomadee

import net.besttoolbars.lomadee.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class LomadeeTransactionReportApiTest {
    private val mockWebServer = MockWebServer()

    @AfterEach
    fun ternDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `create token`() {
        val response = """{"token":"access token"}"""
        val expected = Token("access token")

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val lomadeeApi = LomadeeApi.provider(mockWebServer.url("/").toString())
        val token = lomadeeApi.createToken("", "").get()
        Assertions.assertEquals(expected, token)
    }

    @Test
    fun `fetch transaction report`() {
        val response = """
            <result xmlns="urn:lomadee">
              <details>
                <timestamp>2020-08-13 10:59:59</timestamp>
                <code>200</code>
                <elapsedTime>0</elapsedTime>
                <message>Success</message>
                <status>Success</status>
              </details>
              <item>
                  <date>13/08/2020</date>
                  <currency>BRL</currency>
                  <commission>0.00</commission>
                  <associateId />
                  <site>
                     <siteId>00000</siteId>
                     <siteName>Nome do Site</siteName>
                  </site>
                  <advertiser>
                     <advertiserId>00</advertiserId>
                     <advertiserName>Nome da Loja</advertiserName>
                  </advertiser>
                  <events>
                     <event>
                        <eventId>ID do evento</eventId>
                        <eventName>Nome do evento</eventName>
                        <commission>0.00</commission>
                        <gmv>0.00</gmv>
                     </event>
                  </events>
                  <application>
                     <applicationId>000</applicationId>
                     <applicationName>Nome da Aplicação</applicationName>
                  </application>
                  <gmv>0.00</gmv>
                  <transactionCode>Código da Transação</transactionCode>
                  <transactionId>00000</transactionId>
                  <statusId>0</statusId>
                  <statusName>STATUS</statusName>
                  <processedDate>13/08/2020</processedDate>
                  <clickDate>13/08/2020</clickDate>
                  <checkoutDate>13/08/2020</checkoutDate>
              </item>
            </result>
        """.trimIndent()
        val expected = TransactionReport(
            details = Details(
                timestamp = LocalDateTime.of(2020, 8, 13, 10, 59, 59),
                code = 200,
                elapsedTime = 0,
                message = "Success",
                status = "Success"
            ),
            item = Item(
                date = LocalDate.of(2020, 8, 13),
                currency = "BRL",
                commission = 0.0,
                associateId = null,
                site = Site(0, "Nome do Site"),
                advertiser = Advertiser(0, "Nome da Loja"),
                events = listOf(
                    TransactionEvent(
                        eventId = "ID do evento",
                        eventName = "Nome do evento",
                        commission = 0.0,
                        gmv = 0.0
                    )
                ),
                application = Application(0, "Nome da Aplicação"),
                gmv = 0.0,
                transactionCode = "Código da Transação",
                transactionId = 0,
                statusId = 0,
                statusName = "STATUS",
                processedDate = LocalDate.of(2020, 8, 13),
                clickDate = LocalDate.of(2020, 8, 13),
                checkoutDate = LocalDate.of(2020, 8, 13)
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val lomadeeTransactionApi = LomadeeTransactionApi.provider(mockWebServer.url("/").toString())
        val report = lomadeeTransactionApi.reportTransaction("", "",
            QueryDate.of(2020, 8, 13),
            QueryDate.of(2020, 8, 1),
            3)
            .get()
        Assertions.assertEquals(expected, report)
    }
}