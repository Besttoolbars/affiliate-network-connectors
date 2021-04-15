package net.besttoolbars.dcm.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.besttoolbars.dcm.deserializers.DCMBooleanDeserializer
import net.besttoolbars.dcm.deserializers.DCMEmptyStringDeserializer

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
    val currency: String? = null,

    @JsonProperty("default_goal_name")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val defaultGoalName: String?,

    @JsonProperty("default_payout")
    val defaultPayout: Double,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val description: String? = null,

    @JsonProperty("dne_download_url")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val dneDownloadUrl: String? = null,

    @JsonProperty("dne_list_id")
    val dneListId: Int? = null,

    @JsonProperty("dne_unsubscribe_url")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val dneUnsubscribeUrl: String?,

    @JsonProperty("email_instructions")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val emailInstructions: Boolean,

    @JsonProperty("email_instructions_from")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val emailInstructionsFrom: String? = null,

    @JsonProperty("email_instructions_subject")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val emailInstructionsSubject: String? = null,

    @JsonProperty("expiration_date")
    val expirationDate: String,

    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val featured: String? = null,

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
    val monthlyPayoutCap: Double,

    val name: String,

    @JsonProperty("payout_cap")
    val payoutCap: Double,

    @JsonProperty("payout_type")
    val payoutType: PayoutType,

    @JsonProperty("percent_payout")
    val percentPayout: Double,

    @JsonProperty("preview_url")
    @JsonDeserialize(using = DCMEmptyStringDeserializer::class)
    val previewUrl: String? = null,

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
    val termsAndConditions: String? = null,

    @JsonProperty("use_target_rules")
    @JsonDeserialize(using = DCMBooleanDeserializer::class)
    val useTargetRules: Boolean
)

data class DCMAffiliateOffer(
    val id: Int,

    @JsonProperty("affiliate_id")
    val affiliateId: Int,

    @JsonProperty("offer_id")
    val offerId: Int,

    @JsonProperty("approval_status")
    val approvalStatus: String?,

    @JsonProperty("agreed_terms_and_conditions")
    val agreedTermsAndConditions: String?
)

enum class PayoutType {
    CPA_FLAT,
    CPA_PERCENTAGE,
    CPA_BOTH,
    CPC,
    CPM
}

enum class ProtocolType {
    HTTP,
    HTTPS,
    HTTP_IMG,
    HTTPS_IMG,
    SERVER,
    SERVER_AFFILIATE,
    MOBILE_APP,
    MOBILE_DEVICE,
    PAY_PER_CALL
}

enum class OfferStatus {
    ACTIVE
}