package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */

data class MonetaryAccountResponse(
    @SerializedName("Response") val monetaryAccountList: ArrayList<MonetaryAccountResponseItem>? = null,
    @SerializedName("Pagination") val pagination: Pagination? = null
) {
    fun getFirstAccountID() = monetaryAccountList?.firstOrNull()?.monetaryAccountBank?.id.toString()

}

data class MonetaryAccountResponseItem(
    @SerializedName("MonetaryAccountBank")
    val monetaryAccountBank: MonetaryAccountBank? = null
)

data class MonetaryAccountBank(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("updated")
    val updated: String? = null,
    @SerializedName("alias")
    val alias: List<Alia>? = null,
    @SerializedName("avatar")
    val avatar: Avatar? = null,
    @SerializedName("balance")
    val balance: Balance? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("daily_limit")
    val dailyLimit: DailyLimit? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("public_uuid")
    val publicUuid: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("sub_status")
    val subStatus: String? = null,
    @SerializedName("timezone")
    val timezone: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("monetary_account_profile")
    val monetaryAccountProfile: MonetaryAccountProfile? = null,
    @SerializedName("setting")
    val setting: Setting? = null,
    @SerializedName("connected_cards")
    val connectedCards: List<Any>? = null,
    @SerializedName("overdraft_limit")
    val overdraftLimit: OverdraftLimit? = null,
    @SerializedName("all_auto_save_id")
    val allAutoSaveId: List<Any>? = null,
    @SerializedName("total_request_pending")
    val totalRequestPending: TotalRequestPending? = null
)

data class Balance(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class DailyLimit(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class MonetaryAccountProfile(
    @SerializedName("profile_fill")
    val profileFill: Any? = null,
    @SerializedName("profile_drain")
    val profileDrain: Any? = null,
    @SerializedName("profile_action_required")
    val profileActionRequired: String? = null,
    @SerializedName("profile_amount_required")
    val profileAmountRequired: ProfileAmountRequired? = null
)

data class Setting(
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("icon")
    val icon: Any? = null,
    @SerializedName("default_avatar_status")
    val defaultAvatarStatus: String? = null,
    @SerializedName("restriction_chat")
    val restrictionChat: String? = null,
    @SerializedName("sdd_expiration_action")
    val sddExpirationAction: String? = null
)

data class OverdraftLimit(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class TotalRequestPending(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)


data class ProfileAmountRequired(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
) 