package net.besttoolbars.impactradius.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

//<Campaign>
//            <AdvertiserId>325808</AdvertiserId>
//            <AdvertiserName>L'Oréal Canada</AdvertiserName>
//            <AdvertiserUrl>http://www.loreal.ca</AdvertiserUrl>
//            <CampaignId>4827</CampaignId>
//            <CampaignName>Vichy Canada</CampaignName>
//            <CampaignUrl>http://www.vichy.ca/</CampaignUrl>
//            <CampaignDescription>Vichy Laboratoires is the nº1 most trusted skincare brand in Canadian drugstores. It offers the best treatments in anti-aging, anti-acne, hydration, and complete routines with cleansers, serums and moisturizers. </CampaignDescription>
//            <ShippingRegions>
//                <ShippingRegion>CANADA</ShippingRegion>
//            </ShippingRegions>
//            <CampaignLogoUri>/display-logo-via-campaign/4827.gif</CampaignLogoUri>
//            <PublicTermsUri>/Mediapartners/IRgFjx2fCztq1809157jJ7Pv3FUQp4oNA1/Campaigns/4827/PublicTerms</PublicTermsUri>
//            <ContractStatus>Active</ContractStatus>
//            <ContractUri>/Mediapartners/IRgFjx2fCztq1809157jJ7Pv3FUQp4oNA1/Campaigns/4827/Contracts/33585</ContractUri>
//            <TrackingLink>http://Vichy-CA.hblm3c.net/c/1809157/314483/4827</TrackingLink>
//            <AllowsDeeplinking>true</AllowsDeeplinking>
//            <DeeplinkDomains>
//                <DeeplinkDomain>*vichy.ca</DeeplinkDomain>
//                <DeeplinkDomain> *lengow.com</DeeplinkDomain>
//            </DeeplinkDomains>
//            <Uri>/Mediapartners/IRgFjx2fCztq1809157jJ7Pv3FUQp4oNA1/Campaigns/4827</Uri>
//        </Campaign>

//    <Campaigns page="1" numpages="1" pagesize="100" total="12" start="0" end="11" uri="/Mediapartners/IRgFjx2fCztq1809157jJ7Pv3FUQp4oNA1/Campaigns" firstpageuri="/Mediapartners/IRgFjx2fCztq1809157jJ7Pv3FUQp4oNA1/Campaigns?PageSize=100&amp;Page=1" previouspageuri="" nextpageuri="" lastpageuri="/Mediapartners/IRgFjx2fCztq1809157jJ7Pv3FUQp4oNA1/Campaigns?PageSize=100&amp;Page=1">

@JacksonXmlRootElement(localName = "ImpactRadiusResponse")
data class ImpactRadiusCampaignsResponse(
    @JacksonXmlProperty(localName = "Campaigns")
    val campaignsWrapper: ImpactRadiusCampaigns
)

data class ImpactRadiusCampaigns(
    @JacksonXmlProperty(localName = "page", isAttribute = true)
    val page: Int,
    @JacksonXmlProperty(localName = "numpages", isAttribute = true)
    val numpages: Int,
    @JacksonXmlProperty(localName = "total", isAttribute = true)
    val total: Int,
    @JacksonXmlProperty(localName = "start", isAttribute = true)
    val start: Int,
    @JacksonXmlProperty(localName = "end", isAttribute = true)
    val end: Int,
    @JacksonXmlProperty(localName = "uri", isAttribute = true)
    val uri: String,
    @JacksonXmlProperty(localName = "firstpageuri", isAttribute = true)
    val firstpageuri: String?,
    @JacksonXmlProperty(localName = "previouspageuri", isAttribute = true)
    val previouspageuri: String?,
    @JacksonXmlProperty(localName = "nextpageuri", isAttribute = true)
    val nextpageuri: String?,
    @JacksonXmlProperty(localName = "lastpageuri", isAttribute = true)
    val lastpageuri: String?,
    @JacksonXmlProperty(localName = "Campaign")
    @JacksonXmlElementWrapper(localName = "Campaign", useWrapping = false)
    val campaigns: List<ImpactRadiusCampaign> = listOf()
)

data class ImpactRadiusCampaign(
    @JacksonXmlProperty(localName = "AdvertiserId")
    val advertiserId: String,
    @JacksonXmlProperty(localName = "AdvertiserName")
    val advertiserName: String?,
    @JacksonXmlProperty(localName = "AdvertiserUrl")
    val advertiserUrl: String,
    @JacksonXmlProperty(localName = "CampaignId")
    val campaignId: String,
    @JacksonXmlProperty(localName = "CampaignName")
    val campaignName: String,
    @JacksonXmlProperty(localName = "CampaignUrl")
    val campaignUrl: String,
    @JacksonXmlProperty(localName = "CampaignDescription")
    val campaignDescription: String,
    @JacksonXmlElementWrapper(localName = "ShippingRegions", useWrapping = true)
    @JacksonXmlProperty(localName = "ShippingRegions")
    val shippingRegions: List<String>,
    @JacksonXmlProperty(localName = "CampaignLogoUri")
    val campaignLogoUri: String,
    @JacksonXmlProperty(localName = "PublicTermsUri")
    val publicTermsUri: String,
    @JacksonXmlProperty(localName = "ContractStatus")
    val contractStatus: String,
    @JacksonXmlProperty(localName = "ContractUri")
    val contractUri: String,
    @JacksonXmlProperty(localName = "TrackingLink")
    val trackingLink: String?,
    @JacksonXmlProperty(localName = "AllowsDeeplinking")
    val allowsDeeplinking: Boolean,
    @JacksonXmlElementWrapper(localName = "DeeplinkDomains", useWrapping = true)
    @JacksonXmlProperty(localName = "DeeplinkDomains")
    val deeplinkDomains: List<String>,
    @JacksonXmlProperty(localName = "Uri")
    val uri: String
)
