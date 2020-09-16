package net.besttoolbars.awin.response

import net.besttoolbars.awin.CommissionType

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
)