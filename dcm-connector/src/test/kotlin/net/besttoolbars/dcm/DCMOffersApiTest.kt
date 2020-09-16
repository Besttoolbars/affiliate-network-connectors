package net.besttoolbars.dcm

import net.besttoolbars.dcm.response.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DCMOffersApiTest {
    private val mockWebServer = MockWebServer()
    private val mockedApi =
        DCMOffersApi.provider(mockWebServer.url("/").toString())

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get offers`() {
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
        val actual = mockedApi.getOffers(
            apiKey = "",
            page = 1,
            limit = 20
        ).get()

        val expected = DCMApiResponse<DCMOfferList>(
            request = Any(),
            response = DCMResponse<DCMOfferList>(
                status = 1,
                httpStatus = 200,
                data = DCMOfferList(
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
                ),
                errorMessage = null,
                errors = emptyList()
            )
        )

        Assertions.assertEquals(expected.response, actual.response)
    }
}