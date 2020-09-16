package net.besttoolbars.dcm.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.converters.DCMBooleanDeserializer
import net.besttoolbars.dcm.converters.DCMEmptyStringDeserializer

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class DCMOffer(
    @JsonProperty("allow_direct_links")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val allowDirectLinks: Boolean,

    @JsonProperty("allow_website_links")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val allowWebsiteLinks: Boolean,

    @JsonProperty("conversion_cap")
    val conversionCap: Int,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val currency: String?,

    @JsonProperty("default_goal_name")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val defaultGoalName: String?,

    @JsonProperty("default_payout")
    val defaultPayout: Float,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val description: String?,

    @JsonProperty("dne_download_url")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val dneDownloadUrl: String?,

    @JsonProperty("dne_list_id")
    val dneListId: Int?,

    @JsonProperty("dne_unsubscribe_url")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val dneUnsubscribeUrl: String?,

    @JsonProperty("email_instructions")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val emailInstructions: Boolean,

    @JsonProperty("email_instructions_from")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val emailInstructionsFrom: String?,

    @JsonProperty("email_instructions_subject")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val emailInstructionsSubject: String?,

    @JsonProperty("expiration_date")
    val expirationDate: String,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val featured: String?,

    @JsonProperty("has_goals_enabled")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val hasGoalsEnabled: Boolean,

    val id: Int,

    @JsonProperty("is_expired")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val isExpired: Boolean,

    @JsonProperty("is_pay_per_call")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val isPayPerCall: Boolean = false,

    val modified: Int,

    @JsonProperty("monthly_conversion_cap")
    val monthlyConversionCap: Int,

    @JsonProperty("monthly_payout_cap")
    val monthlyPayoutCap: Float,

    val name: String,

    @JsonProperty("payout_cap")
    val payoutCap: Float,

    @JsonProperty("payout_type")
    val payoutType: PayoutType,

    @JsonProperty("percent_payout")
    val percentPayout: Float,

    @JsonProperty("preview_url")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val previewUrl: String?,

    val protocol: ProtocolType,

    @JsonProperty("require_approval")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val requireApproval: Boolean,

    @JsonProperty("require_terms_and_conditions")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val requireTermsAndConditions: Boolean,

    @JsonProperty("show_custom_variables")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val showCustomVariables: Boolean,

    @JsonProperty("show_mail_list")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val showMailList: Boolean,

    val status: OfferStatus,

    @JsonProperty("terms_and_conditions")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val termsAndConditions: String?,

    @JsonProperty("use_target_rules")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val useTargetRules: Boolean
)

enum class PayoutType {
    @JsonProperty("cpa_flat")
    CPA_FLAT,

    @JsonProperty("cpa_percentage")
    CPA_PERCENTAGE,

    @JsonProperty("cpa_both")
    CPA_BOTH,

    @JsonProperty("cpc")
    CPC,

    @JsonProperty("cpm")
    CPM
}

enum class ProtocolType {
    @JsonProperty("http")
    HTTP,

    @JsonProperty("https")
    HTTPS,

    @JsonProperty("http_img")
    HTTP_IMG,

    @JsonProperty("https_img")
    HTTPS_IMG,

    @JsonProperty("server")
    SERVER,

    @JsonProperty("server_affiliate")
    SERVER_AFFILIATE,

    @JsonProperty("mobile_app")
    MOBILE_APP,

    @JsonProperty("mobile_device")
    MOBILE_DEVICE,

    @JsonProperty("pay_per_call")
    PAY_PER_CALL
}

enum class OfferStatus {
    @JsonProperty("active")
    ACTIVE
}