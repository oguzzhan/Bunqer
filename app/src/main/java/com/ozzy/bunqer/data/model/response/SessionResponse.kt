package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
data class SessionResponse(
    @SerializedName("Response")
    val response: List<Response>? = null
) {
    fun getToken(): String {
        return response?.filter { it.token != null }?.firstOrNull()?.token?.token ?: ""
    }
}

data class Response(
    @SerializedName("Id")
    val id: Id? = null,
    @SerializedName("Token")
    val token: SessionToken? = null,
    @SerializedName("UserPerson")
    val userPerson: UserPerson? = null
)

data class SessionToken(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("updated")
    val updated: String? = null,
    @SerializedName("token")
    val token: String? = null
)

data class UserPerson(
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
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("sub_status")
    val subStatus: String? = null,
    @SerializedName("public_uuid")
    val publicUuid: String? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("public_nick_name")
    val publicNickName: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("region")
    val region: String? = null,
    @SerializedName("session_timeout")
    val sessionTimeout: Int? = null,
    @SerializedName("daily_limit_without_confirmation_login")
    val dailyLimitWithoutConfirmationLogin: DailyLimitWithoutConfirmationLogin? = null,
    @SerializedName("relations")
    val relations: List<Any>? = null,
    @SerializedName("notification_filters")
    val notificationFilters: List<NotificationFilter>? = null,
    @SerializedName("address_main")
    val addressMain: AddressMain? = null,
    @SerializedName("address_postal")
    val addressPostal: AddressPostal? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("middle_name")
    val middleName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("legal_name")
    val legalName: String? = null,
    @SerializedName("tax_resident")
    val taxResident: Any? = null,
    @SerializedName("date_of_birth")
    val dateOfBirth: String? = null,
    @SerializedName("place_of_birth")
    val placeOfBirth: String? = null,
    @SerializedName("country_of_birth")
    val countryOfBirth: String? = null,
    @SerializedName("nationality")
    val nationality: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("version_terms_of_service")
    val versionTermsOfService: String? = null,
    @SerializedName("deny_reason")
    val denyReason: Any? = null,
    @SerializedName("document_status")
    val documentStatus: String? = null,
    @SerializedName("is_primary_document")
    val isPrimaryDocument: Boolean? = null,
    @SerializedName("customer")
    val customer: Customer? = null,
    @SerializedName("customer_limit")
    val customerLimit: CustomerLimit? = null,
    @SerializedName("billing_contract")
    val billingContract: List<BillingContract>? = null,
    @SerializedName("pack_membership")
    val packMembership: Any? = null,
    @SerializedName("premium_trial")
    val premiumTrial: Any? = null,
    @SerializedName("joint_membership")
    val jointMembership: Any? = null
)

data class Alia(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("value")
    val value: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Avatar(
    @SerializedName("uuid")
    val uuid: String? = null,
    @SerializedName("image")
    val image: List<Image>? = null,
    @SerializedName("anchor_uuid")
    val anchorUuid: String? = null,
    @SerializedName("style")
    val style: String? = null
)

data class DailyLimitWithoutConfirmationLogin(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class NotificationFilter(
    @SerializedName("notification_delivery_method")
    val notificationDeliveryMethod: String? = null,
    @SerializedName("category")
    val category: String? = null
)

data class AddressMain(
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("house_number")
    val houseNumber: String? = null,
    @SerializedName("postal_code")
    val postalCode: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("province")
    val province: Any? = null,
    @SerializedName("extra")
    val extra: Any? = null,
    @SerializedName("mailbox_name")
    val mailboxName: Any? = null
)

data class AddressPostal(
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("house_number")
    val houseNumber: String? = null,
    @SerializedName("postal_code")
    val postalCode: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("province")
    val province: Any? = null,
    @SerializedName("extra")
    val extra: Any? = null,
    @SerializedName("mailbox_name")
    val mailboxName: String? = null
)

data class Customer(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("updated")
    val updated: String? = null,
    @SerializedName("billing_account_id")
    val billingAccountId: Int? = null,
    @SerializedName("invoice_notification_preference")
    val invoiceNotificationPreference: String? = null
)

data class CustomerLimit(
    @SerializedName("limit_monetary_account")
    val limitMonetaryAccount: Int? = null,
    @SerializedName("limit_monetary_account_remaining")
    val limitMonetaryAccountRemaining: Int? = null,
    @SerializedName("limit_card_debit_maestro")
    val limitCardDebitMaestro: Int? = null,
    @SerializedName("limit_card_debit_mastercard")
    val limitCardDebitMastercard: Int? = null,
    @SerializedName("limit_card_wildcard")
    val limitCardWildcard: Int? = null,
    @SerializedName("limit_card_debit_wildcard")
    val limitCardDebitWildcard: Int? = null,
    @SerializedName("limit_card_debit_maestro_virtual_subscription")
    val limitCardDebitMaestroVirtualSubscription: Int? = null,
    @SerializedName("limit_card_debit_maestro_virtual_total")
    val limitCardDebitMaestroVirtualTotal: Int? = null,
    @SerializedName("limit_card_debit_mastercard_virtual_subscription")
    val limitCardDebitMastercardVirtualSubscription: Int? = null,
    @SerializedName("limit_card_debit_mastercard_virtual_total")
    val limitCardDebitMastercardVirtualTotal: Int? = null,
    @SerializedName("limit_card_replacement")
    val limitCardReplacement: Int? = null,
    @SerializedName("limit_amount_monthly")
    val limitAmountMonthly: Any? = null,
    @SerializedName("spent_amount_monthly")
    val spentAmountMonthly: Any? = null,
    @SerializedName("limit_card_credit_mastercard")
    val limitCardCreditMastercard: Int? = null
)

data class BillingContract(
    @SerializedName("BillingContractSubscription")
    val billingContractSubscription: BillingContractSubscription? = null
)

data class Image(
    @SerializedName("attachment_public_uuid")
    val attachmentPublicUuid: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("content_type")
    val contentType: String? = null
)

data class BillingContractSubscription(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("updated")
    val updated: String? = null,
    @SerializedName("contract_date_start")
    val contractDateStart: String? = null,
    @SerializedName("contract_date_end")
    val contractDateEnd: Any? = null,
    @SerializedName("contract_version")
    val contractVersion: Int? = null,
    @SerializedName("subscription_type")
    val subscriptionType: String? = null,
    @SerializedName("subscription_type_downgrade")
    val subscriptionTypeDowngrade: Any? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("sub_status")
    val subStatus: String? = null
) 