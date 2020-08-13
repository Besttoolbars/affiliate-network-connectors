package net.besttoolbars.lomadee

import net.besttoolbars.lomadee.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException
import java.time.LocalDateTime

internal class LomadeeCouponsApiTest {
    private val mockWebServer = MockWebServer()
    private val lomadeeCouponsApi: LomadeeCouponsApi =
        LomadeeApi.provider(mockWebServer.url("/").toString())

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch coupons stores`() {
        val response = """
            {
              "requestInfo": {
                "status": "OK",
                "message": "SUCCESS",
                "generatedDate": null
              },
              "stores": [
                {
                  "id": 5953,
                  "name": "Zattini",
                  "image": "https://www.lomadee.com/programas/BR/5953/logo_185x140.png",
                  "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                },
                {
                  "id": 6061,
                  "name": "Gear Best",
                  "image": "https://www.lomadee.com/programas/BR/6061/logo_185x140.png",
                  "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                }
              ]
            }
        """.trimIndent()

        val expected = CouponStoreResponse(
            requestInfo = RequestInfo("OK", "SUCCESS"),
            stores = listOf(
                CouponStore(
                    id = 5953,
                    name = "Zattini",
                    image = "https://www.lomadee.com/programas/BR/5953/logo_185x140.png",
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                ),
                CouponStore(
                    id = 6061,
                    name = "Gear Best",
                    image = "https://www.lomadee.com/programas/BR/6061/logo_185x140.png",
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val stores = lomadeeCouponsApi.couponStores("", "").get()
        Assertions.assertEquals(expected, stores)
    }

    @Test
    fun `fetch coupons categories`() {
        val response = """
            {
              "requestInfo": {
                "status": "OK",
                "message": "SUCCESS",
                "generatedDate": null
              },
              "categories": [
                {
                  "id": 99004,
                  "name": "Moda e Acessórios",
                  "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                },
                {
                  "id": 99003,
                  "name": "Eletrodomésticos",
                  "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                }
              ]
            }
        """.trimIndent()

        val expected = CouponCategoryResponse(
            requestInfo = RequestInfo("OK", "SUCCESS"),
            categories = listOf(
                CouponCategory(
                    id = 99004,
                    name = "Moda e Acessórios",
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                ),
                CouponCategory(
                    id = 99003,
                    name = "Eletrodomésticos",
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val categories = lomadeeCouponsApi.couponCategories("", "").get()
        Assertions.assertEquals(expected, categories)
    }

    @Test
    fun `fetch coupons`() {
        val response = """
            {
              "requestInfo": {
                "status": "OK",
                "message": "SUCCESS",
                "generatedDate": null
              },
              "pagination": {
                "page": 1,
                "size": 2,
                "totalSize": 22,
                "totalPage": 11
              },
              "coupons": [
                {
                  "id": 9763,
                  "description": "20% de desconto em todos produtos vendidos e entregues pela Zattini.",
                  "code": "TOPZAT",
                  "discount": 20.0,
                  "store": {
                    "id": 5953,
                    "name": "Zattini",
                    "image": "https://www.lomadee.com/programas/BR/5953/logo_185x140.png",
                    "link": "http://www.zattini.com.br/"
                  },
                  "category": {
                    "id": 99004,
                    "name": "Moda e Acessórios"
                  },
                  "vigency": "06/09/2020 23:59:00",
                  "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d",
                  "new": true
                },
                {
                  "id": 9762,
                  "description": "10%  de desconto em seleção de produtos na Henring.",
                  "code": "ZATHERING",
                  "discount": 10.0,
                  "store": {
                    "id": 5953,
                    "name": "Zattini",
                    "image": "https://www.lomadee.com/programas/BR/5953/logo_185x140.png",
                    "link": "http://www.zattini.com.br/"
                  },
                  "category": {
                    "id": 99004,
                    "name": "Moda e Acessórios"
                  },
                  "vigency": "07/09/2020 23:59:00",
                  "link": "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d",
                  "new": true
                }
              ]
            }
        """.trimIndent()

        val expected = CouponResponse(
            requestInfo = RequestInfo("OK", "SUCCESS"),
            pagination = Pagination(1, 2, 22, 11),
            coupons = listOf(
                Coupon(
                    id = 9763,
                    description = "20% de desconto em todos produtos vendidos e entregues pela Zattini.",
                    code = "TOPZAT",
                    discount = 20,
                    store = CouponStore(
                        id = 5953,
                        name = "Zattini",
                        image = "https://www.lomadee.com/programas/BR/5953/logo_185x140.png",
                        link = "http://www.zattini.com.br/"
                    ),
                    category = CouponCategory(
                        id = 99004,
                        name = "Moda e Acessórios"
                    ),
                    vigency = LocalDateTime.of(2020, 9, 6, 23, 59),
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                ),
                Coupon(
                    id = 9762,
                    description = "10%  de desconto em seleção de produtos na Henring.",
                    code = "ZATHERING",
                    discount = 10,
                    store = CouponStore(
                        id = 5953,
                        name = "Zattini",
                        image = "https://www.lomadee.com/programas/BR/5953/logo_185x140.png",
                        link = "http://www.zattini.com.br/"
                    ),
                    category = CouponCategory(
                        id = 99004,
                        name = "Moda e Acessórios"
                    ),
                    vigency = LocalDateTime.of(2020, 9, 7, 23, 59),
                    link = "https://developer.lomadee.com/redir/validation/?sourceId=36712392&appToken=1596966044499ec5f428d"
                )
            )
        )

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
        val coupons = lomadeeCouponsApi.coupons("", "").get()
        Assertions.assertEquals(expected, coupons)
    }
}