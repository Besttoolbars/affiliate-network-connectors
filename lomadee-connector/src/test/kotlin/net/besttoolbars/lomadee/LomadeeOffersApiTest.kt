package net.besttoolbars.lomadee

import net.besttoolbars.lomadee.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class LomadeeOffersApiTest {
    private val mockWebServer = MockWebServer()
    private val lomadeeOffersApi: LomadeeOffersApi =
        LomadeeApi.provider(mockWebServer.url("/").toString())

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch offers stores`() {
        val response = """
            {
                 "requestInfo": {
                    "status": "OK",
                    "message": "SUCCESS",
                    "generatedDate": null
                  },
                  "pagination": {
                    "page": 1,
                    "size": 210,
                    "totalSize": 210,
                    "totalPage": 1
                  },
                  "stores": [
                    {
                      "id": 5767,
                      "name": "Onofre Eletro",
                      "thumbnail": "https://www.lomadee.com/programas/BR/5767/logo_115x76.png",
                      "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d",
                      "hasOffer": 0,
                      "maxCommission": 2.9699999999999993,
                      "events": [
                        {
                          "event": "Onofre Eletro - Vendas",
                          "eventType": "Sale",
                          "fixedCommission": false,
                          "commission": 0.029699999999999994
                        }
                      ]
                    }
                  ]
            }
        """.trimIndent()

        val expected = OfferStoresResponse(
            requestInfo = RequestInfo("OK", "SUCCESS"),
            pagination = Pagination(1, 210, 210, 1),
            stores = listOf(
                OfferStore(
                    id = 5767,
                    name = "Onofre Eletro",
                    thumbnail = "https://www.lomadee.com/programas/BR/5767/logo_115x76.png",
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d",
                    hasOffer = 0,
                    maxCommission = 2.9699999999999993,
                    events = listOf(
                        OfferEvent(
                            event = "Onofre Eletro - Vendas",
                            eventType = "Sale",
                            fixedCommission = false,
                            commission = 0.029699999999999994
                        )
                    )
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val stores = lomadeeOffersApi.offerStores("", "").get()
        Assertions.assertEquals(expected, stores)
    }

    @Test
    fun `fetch offers categories`() {
        val response = """
            {
                 "requestInfo": {
                    "status": "OK",
                    "message": "SUCCESS",
                    "generatedDate": null
                  },
                  "pagination": {
                    "page": 1,
                    "size": 5957,
                    "totalSize": 5957,
                    "totalPage": 1
                  },
                  "categories": [
                    {
                      "id": 1,
                      "name": "Eletrônicos",
                      "hasOffer": 0,
                      "link": "http://api.lomadee.com/v3/1596966044499ec5f428d/category/_id/1?sourceId=36712392"
                    },
                    {
                      "id": 2,
                      "name": "Informática",
                      "hasOffer": 0,
                      "link": "http://api.lomadee.com/v3/1596966044499ec5f428d/category/_id/2?sourceId=36712392"
                    }
                  ]
            }
        """.trimIndent()

        val expected = OfferCategoriesResponse(
            requestInfo = RequestInfo("OK", "SUCCESS"),
            pagination = Pagination(1, 5957, 5957, 1),
            categories = listOf(
                OfferCategory(
                    id = 1,
                    name = "Eletrônicos",
                    link = "http://api.lomadee.com/v3/1596966044499ec5f428d/category/_id/1?sourceId=36712392",
                    hasOffer = 0
                ),
                OfferCategory(
                    id = 2,
                    name = "Informática",
                    link = "http://api.lomadee.com/v3/1596966044499ec5f428d/category/_id/2?sourceId=36712392",
                    hasOffer = 0
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val categories = lomadeeOffersApi.offerCategories("", "").get()
        Assertions.assertEquals(expected, categories)
    }

    @Test
    fun `fetch offers by store`() {
        val response = """
            {
                 "requestInfo": {
                    "status": "OK",
                    "message": "SUCCESS",
                    "generatedDate": null
                  },
                  "pagination": {
                    "page": 1,
                    "size": 100,
                    "totalSize": 673,
                    "totalPage": 7
                  },
                  "offers": [
                    {
                      "id": "1808",
                      "name": "Cartão com foto horizontal - enviar foto para fotos@foundit.com.br",
                      "category": {
                        "id": 0,
                        "name": "Geral",
                        "link": "http://api.lomadee.com/v3/1596966044499ec5f428d/category/_id/0?sourceId=36712392"
                      },
                      "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d",
                      "thumbnail": "https://foundit.vteximg.com.br/arquivos/ids/160808_2",
                      "price": 14.0,
                      "installment": {
                        "quantity": 1,
                        "value": 14.0
                      },
                      "store": {
                        "id": 6433,
                        "name": "Found IT",
                        "thumbnail": "https://www.lomadee.com/programas/BR/6433/imagemBox_80x60.png",
                        "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                      }
                    }
                  ]
            }
        """.trimIndent()

        val expected = OffersResponse(
            requestInfo = RequestInfo("OK", "SUCCESS"),
            pagination = Pagination(1, 100, 673, 7),
            offers = listOf(
                Offer(
                    id = "1808",
                    name = "Cartão com foto horizontal - enviar foto para fotos@foundit.com.br",
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d",
                    category = OfferCategory(
                        id = 0,
                        name = "Geral",
                        link = "http://api.lomadee.com/v3/1596966044499ec5f428d/category/_id/0?sourceId=36712392"
                    ),
                    thumbnail = "https://foundit.vteximg.com.br/arquivos/ids/160808_2",
                    installment = OfferInstallment(1, 14.0),
                    price = 14,
                    store = OfferStore(
                        id = 6433,
                        name = "Found IT",
                        thumbnail= "https://www.lomadee.com/programas/BR/6433/imagemBox_80x60.png",
                        link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                    )
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val offers = lomadeeOffersApi.offersByStore("", 6433,"").get()
        Assertions.assertEquals(expected, offers)
    }
}