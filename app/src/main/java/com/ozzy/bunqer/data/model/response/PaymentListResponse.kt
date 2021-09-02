package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
data class PaymentListResponse(
    @SerializedName("Response")
    val response: List<PaymentResponse>? = null,
    @SerializedName("Pagination")
    val pagination: Pagination? = null
)

data class PaymentResponse(
    @SerializedName("Payment")
    val payment: Payment? = null
)

data class Payment(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("updated")
    val updated: String? = null,
    @SerializedName("monetary_account_id")
    val monetaryAccountId: Int? = null,
    @SerializedName("amount")
    val amount: Amount? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("merchant_reference")
    val merchantReference: Any? = null,
    @SerializedName("alias")
    val alias: Alias? = null,
    @SerializedName("counterparty_alias")
    val counterpartyAlias: CounterpartyAlias? = null,
    @SerializedName("attachment")
    val attachment: List<Any>? = null,
    @SerializedName("geolocation")
    val geolocation: Any? = null,
    @SerializedName("batch_id")
    val batchId: Any? = null,
    @SerializedName("scheduled_id")
    val scheduledId: Any? = null,
    @SerializedName("address_billing")
    val addressBilling: Any? = null,
    @SerializedName("address_shipping")
    val addressShipping: Any? = null,
    @SerializedName("sub_type")
    val subType: String? = null,
    @SerializedName("request_reference_split_the_bill")
    val requestReferenceSplitTheBill: List<Any>? = null,
    @SerializedName("balance_after_mutation")
    val balanceAfterMutation: BalanceAfterMutation? = null,
    @SerializedName("payment_auto_allocate_instance")
    val paymentAutoAllocateInstance: Any? = null
) {
    fun getPersonWhoPaid(): String {
        return if (subType == "REQUEST") {
            "From: ${counterpartyAlias?.displayName}"
        } else {
            "To: ${counterpartyAlias?.displayName}"
        }
    }

    fun getPaymentDescription(): String {
        return "${amount?.value ?: ""} ${amount?.currency ?: ""}"
    }
}


data class Amount(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class Alias(
    @SerializedName("iban")
    val iban: String? = null,
    @SerializedName("is_light")
    val isLight: Boolean? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("avatar")
    val avatar: Avatar? = null,
    @SerializedName("label_user")
    val labelUser: LabelUser? = null,
    @SerializedName("country")
    val country: String? = null
)

data class CounterpartyAlias(
    @SerializedName("iban")
    val iban: String? = null,
    @SerializedName("is_light")
    val isLight: Boolean? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("avatar")
    val avatar: Avatar? = null,
    @SerializedName("label_user")
    val labelUser: LabelUser? = null,
    @SerializedName("country")
    val country: String? = null
)

data class BalanceAfterMutation(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class LabelUser(
    @SerializedName("uuid")
    val uuid: String? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("avatar")
    val avatar: Avatar? = null,
    @SerializedName("public_nick_name")
    val publicNickName: String? = null
)
