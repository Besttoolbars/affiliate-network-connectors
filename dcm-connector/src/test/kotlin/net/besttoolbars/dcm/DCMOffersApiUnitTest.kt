package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DCMOffersApiUnitTest {
    private val mockWebServer = MockWebServer()
    private val mockedApi =
        DCMApi.provider(mockWebServer.url("/").toString())

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getApprovedOffers() {
        val data = """
            {
                "request": {
                    "Target": "Affiliate_Offer",
                    "Format": "json",
                    "Service": "HasOffers",
                    "Version": "2",
                    "Method": "findMyApprovedOffers",
                    "api_key": "62d8e9b6aad1cb1e8063c648c2fc7ea1bba706f401cac3c4256a3d62732605a0",
                    "page": "1",
                    "limit": "1"
                },
                "response": {
                    "status": 1,
                    "httpStatus": 200,
                    "data": {
                        "page": "1",
                        "current": 0,
                        "count": 6,
                        "pageCount": 6,
                        "data": {
                            "1177": {
                                "Offer": {
                                    "id": "1177",
                                    "name": "800 Flower | UAE | CPA | Coupon",
                                    "description": "800flower is an online flower shop.",
                                    "require_approval": "0",
                                    "require_terms_and_conditions": 1,
                                    "terms_and_conditions": "<h3 font-weight: bold>Please read our affiliate terms and conditions carefully before you join our program or begin marketing of our program. </h3>",
                                    "preview_url": "https://www.800flower.ae/",
                                    "currency": "AED",
                                    "default_payout": "15.00000",
                                    "protocol": "https",
                                    "status": "active",
                                    "expiration_date": "2031-12-31 19:59:59",
                                    "payout_type": "cpa_flat",
                                    "percent_payout": "10.50",
                                    "featured": "0000-00-00 00:00:00",
                                    "conversion_cap": "0",
                                    "monthly_conversion_cap": "0",
                                    "payout_cap": "0.00",
                                    "monthly_payout_cap": "0.00",
                                    "allow_multiple_conversions": "0",
                                    "allow_website_links": "1",
                                    "allow_direct_links": "0",
                                    "show_custom_variables": "0",
                                    "session_hours": "4032",
                                    "show_mail_list": "0",
                                    "dne_list_id": "0",
                                    "email_instructions": "0",
                                    "email_instructions_from": "",
                                    "email_instructions_subject": "",
                                    "enforce_secure_tracking_link": "1",
                                    "has_goals_enabled": "0",
                                    "default_goal_name": "",
                                    "modified": 1600158239,
                                    "use_target_rules": "0",
                                    "use_payout_groups": "0",
                                    "link_platform": "",
                                    "is_expired": "0",
                                    "dne_download_url": null,
                                    "dne_unsubscribe_url": null,
                                    "dne_third_party_list": false,
                                    "approval_status": "approved"
                                }
                            }
                        }
                    },
                    "errors": [],
                    "errorMessage": null
                }
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(data))
        val actual = mockedApi.getApprovedOffers(
            apiKey = "",
            page = 1,
            limit = 20
        ).get()

        val expected = DCMApiResponse(
            request = Any(),
            response = DCMResponse(
                status = 1,
                httpStatus = 200,
                data = DCMOfferLimitedList(
                    page = 1,
                    current = 0,
                    count = 6,
                    pageCount = 6,
                    data = mapOf(
                        "1177" to DCMOfferListData(
                            offer = DCMOffer(
                                allowDirectLinks = false,
                                allowWebsiteLinks = true,
                                conversionCap = 0,
                                currency = "AED",
                                defaultGoalName = null,
                                defaultPayout = 15.0f,
                                description = "800flower is an online flower shop.",
                                dneDownloadUrl = null,
                                dneListId = 0,
                                dneUnsubscribeUrl = null,
                                emailInstructions = false,
                                emailInstructionsFrom = null,
                                emailInstructionsSubject = null,
                                expirationDate = "2031-12-31 19:59:59",
                                featured = "0000-00-00 00:00:00",
                                hasGoalsEnabled = false,
                                id = 1177,
                                isExpired = false,
                                isPayPerCall = false,
                                modified = 1600158239,
                                monthlyConversionCap = 0,
                                monthlyPayoutCap = 0.0f,
                                name = "800 Flower | UAE | CPA | Coupon",
                                payoutCap = 0.0f,
                                payoutType = PayoutType.CPA_FLAT,
                                percentPayout = 10.5f,
                                previewUrl = "https://www.800flower.ae/",
                                protocol = ProtocolType.HTTPS,
                                requireApproval = false,
                                requireTermsAndConditions = true,
                                showCustomVariables = false,
                                showMailList = false,
                                status = OfferStatus.ACTIVE,
                                termsAndConditions = "<h3 font-weight: bold>Please read our affiliate terms and conditions carefully before you join our program or begin marketing of our program. </h3>",
                                useTargetRules = false
                            )
                        )
                    )
                )
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }

    @Test
    fun getCategories() {
        val data = """
            {
                "request": {
                    "Target": "Affiliate_Offer",
                    "Format": "json",
                    "Service": "HasOffers",
                    "Version": "2",
                    "api_key": "",
                    "Method": "getCategories",
                    "ids": [
                        "220"
                    ]
                },
                "response": {
                    "status": 1,
                    "httpStatus": 200,
                    "data": [
                        {
                            "offer_id": "220",
                            "categories": {
                                "52": {
                                    "id": "52",
                                    "name": "Gifts & Flowers"
                                }
                            }
                        }
                    ],
                    "errors": [],
                    "errorMessage": null
                }
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(data))
        val actual = mockedApi.getCategories(
            apiKey = "",
            ids = listOf(220)
        ).get()

        val expected = DCMApiResponse(
            request = Any(),
            response = DCMResponse(
                status = 1,
                httpStatus = 200,
                data = listOf(
                    DCMOfferCategory(
                        offerId = 220,
                        categories = mapOf(
                            52 to DCMCategory(
                                id = 52,
                                name = "Gifts & Flowers"
                            )
                        )
                    )
                )
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }

    @Test
    fun getOffersUrls() {
        val data = """
            {
                "request": {
                    "Target": "Affiliate_OfferUrl",
                    "Format": "json",
                    "Service": "HasOffers",
                    "Version": "2",
                    "Method": "findAll",
                    "api_key": "62d8e9b6aad1cb1e8063c648c2fc7ea1bba706f401cac3c4256a3d62732605a0",
                    "limit": "1"
                },
                "response": {
                    "status": 1,
                    "httpStatus": 200,
                    "data": {
                        "page": 1,
                        "current": 0,
                        "count": 1050,
                        "pageCount": 1050,
                        "data": {
                            "17355": {
                                "OfferUrl": {
                                    "id": "17355",
                                    "offer_id": "354",
                                    "name": "GAP Exclusive Sale Up to 70% off ",
                                    "preview_url": "https://www.gap.ae/sale/shop-sale/women/",
                                    "status": "active",
                                    "created": "2020-01-08 01:33:43",
                                    "modified": "2020-04-08 09:33:10"
                                }
                            }
                        }
                    },
                    "errors": [],
                    "errorMessage": null
                }
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(data))
        val actual = mockedApi.getOffersUrls(
            apiKey = "",
            page = 1,
            limit = 1
        ).get()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val expected = DCMApiResponse(
            request = Any(),
            response = DCMResponse(
                status = 1,
                httpStatus = 200,
                data = DCMOfferUrlLimitedList(
                    page = 1,
                    current = 0,
                    count = 1050,
                    pageCount = 1050,
                    data = mapOf(
                        "17355" to DCMOfferUrlListData(
                            offerUrl = DCMOfferUrl(
                                id = 17355,
                                offerId = 354,
                                name = "GAP Exclusive Sale Up to 70% off ",
                                previewUrl = "https://www.gap.ae/sale/shop-sale/women/",
                                status = OfferStatus.ACTIVE,
                                created = LocalDateTime.parse("2020-01-08 01:33:43", formatter),
                                modified = LocalDateTime.parse("2020-04-08 09:33:10", formatter)
                            )
                        )
                    )
                )
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }

    @Test
    fun getOfferFiles() {
        val data = """
            {
                "request": {
                    "Target": "Affiliate_OfferFile",
                    "Format": "json",
                    "Service": "HasOffers",
                    "Version": "2",
                    "Method": "findAll",
                    "api_key": "62d8e9b6aad1cb1e8063c648c2fc7ea1bba706f401cac3c4256a3d62732605a0",
                    "limit": "1"
                },
                "response": {
                    "status": 1,
                    "httpStatus": 200,
                    "data": {
                        "page": 1,
                        "current": 0,
                        "count": 5460,
                        "pageCount": 5460,
                        "data": {
                            "18": {
                                "OfferFile": {
                                    "id": "18",
                                    "offer_id": "10",
                                    "display": "travelwings.com-coupons-codes.jpg",
                                    "filename": "travelwings.com-coupons-codes.jpg",
                                    "size": "5150",
                                    "status": "active",
                                    "type": "offer thumbnail",
                                    "width": "150",
                                    "height": "72",
                                    "code": null,
                                    "flash_vars": null,
                                    "interface": "network",
                                    "account_id": null,
                                    "is_private": "0",
                                    "created": "2016-03-30 06:24:02",
                                    "modified": "0000-00-00 00:00:00",
                                    "url": "https://media.go2speed.org/brand/files/dcm/10/travelwings.com-coupons-codes.jpg",
                                    "thumbnail": "https://media.go2speed.org/brand/files/dcm/10/thumbnails_100/travelwings.com-coupons-codes.jpg"
                                }
                            }
                        }
                    },
                    "errors": [],
                    "errorMessage": null
                }
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(data))
        val actual = mockedApi.getOfferFiles(
            apiKey = "",
            page = 1,
            limit = 1
        ).get()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val expected = DCMApiResponse(
            request = Any(),
            response = DCMResponse(
                status = 1,
                httpStatus = 200,
                data = DCMOfferFileLimitedList(
                    page = 1,
                    current = 0,
                    count = 5460,
                    pageCount = 5460,
                    data = mapOf(
                        "18" to DCMOfferFileListData(
                            offerFile = DCMOfferFile(
                                id = 18,
                                offerId = 10,
                                display = "travelwings.com-coupons-codes.jpg",
                                filename = "travelwings.com-coupons-codes.jpg",
                                size = 5150,
                                status = FileStatus.ACTIVE,
                                type = FileType.OFFER_THUMBNAIL,
                                width = 150,
                                height = 72,
                                code = null,
                                flashVars = null,
                                `interface` = Interface.NETWORK,
                                accountId = null,
                                isPrivate = false,
                                created = LocalDateTime.parse("2016-03-30 06:24:02", formatter),
                                modified = null,
                                url = "https://media.go2speed.org/brand/files/dcm/10/travelwings.com-coupons-codes.jpg",
                                thumbnail = "https://media.go2speed.org/brand/files/dcm/10/thumbnails_100/travelwings.com-coupons-codes.jpg"
                            )
                        )
                    )
                )
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }

    @Test
    fun errorsList() {
        val data = """
            {
                "response": {
                    "errors": [{
                        "publicMessage": "Unsupported output format in URL"
                    }],
                    "link": "http://developers.hasoffers.com/"
                }
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(data))
        val actual = mockedApi.getOfferFiles(
            apiKey = "",
            page = 1,
            limit = 1
        ).get()

        val expected = DCMApiResponse(
            request = Any(),
            response = DCMResponse(
                data = null,
                errors = listOf(
                    DCMError(
                        publicMessage = "Unsupported output format in URL"
                    )
                )
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }

    @Test
    fun errorsObject() {
        val data = """
            {
                "response": {
                    "errors": {
                        "publicMessage": "Unsupported output format in URL"
                    },
                    "link": "http://developers.hasoffers.com/"
                }
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(data))
        val actual = mockedApi.getOfferFiles(
            apiKey = "",
            page = 1,
            limit = 1
        ).get()

        val expected = DCMApiResponse(
            request = Any(),
            response = DCMResponse(
                data = null,
                errors = listOf(
                    DCMError(
                        publicMessage = "Unsupported output format in URL"
                    )
                )
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }

    @Test
    fun test() {
        val api = DCMApi.provider()
        val offers = api.getApprovedOffers(
            apiKey = "62d8e9b6aad1cb1e8063c648c2fc7ea1bba706f401cac3c4256a3d62732605a0",
            page = 1,
            limit = 10000
        ).get()

        val categories = api.getCategories(
            apiKey = "62d8e9b6aad1cb1e8063c648c2fc7ea1bba706f401cac3c4256a3d62732605a0",
            ids = offers.response.data!!.data.values.map { it.offer.id }
        ).get().response.data!!

        categories.map { println(it) }

        val distinctCategories = categories
            .flatMap { it.categories.values }
            .distinctBy { it.id }
            .map { it.id }

        println(distinctCategories)
    }
}