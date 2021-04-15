package net.besttoolbars.dcm

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

@EnabledIfEnvironmentVariable(named = "PartnerDCMApiKey", matches = ".*\\S.*")
class DCMPartnerApiIntegrationTest {
    val api = PartnerDCMApi.provider()
    val apiKey = System.getenv("PartnerDCMApiKey")

    @Test
    fun `should return campaigns`() {
        val campaigns = api.getCampaigns(apiKey).get()
        Assertions.assertNotNull(campaigns.campaigns)
    }

    @Test
    fun `should return codes`() {
        val codes = api.getCodes(apiKey).get()
        Assertions.assertNotNull(codes.campaignCodes)
    }
}