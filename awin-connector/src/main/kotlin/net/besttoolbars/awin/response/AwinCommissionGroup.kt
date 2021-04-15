package net.besttoolbars.awin.response

import net.besttoolbars.awin.AwinCommissionType

data class AwinCommissionGroupsResponse (
    val advertiser: Long,
    val publisher: Long,
    val commissionGroups: List<AwinCommissionGroup> = emptyList()
)

data class AwinCommissionGroup (
    val groupId: Long,
    val groupCode: String? = null,
    val groupName: String,
    val type: AwinCommissionType,

    val percentage: Long? = null,
    val amount: Long? = null,
    val currency: String? = null
)