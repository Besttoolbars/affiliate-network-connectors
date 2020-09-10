package net.besttoolbars.awin.response

data class AwinOffersResponse (
    val advertiser: Long,
    val publisher: Long,
    val commissionGroups: List<CommissionGroup> = emptyList()
)

data class CommissionGroup (
    val groupId: Long,
    val groupCode: String? = null,
    val groupName: String,
    val type: CommissionType,

    val percentage: Long? = null,
    val amount: Long? = null,
    val currency: String? = null
) {
    enum class CommissionType(val type: String) {
        PERCENTAGE("percentage"),
        FIX("fix")
    }
}