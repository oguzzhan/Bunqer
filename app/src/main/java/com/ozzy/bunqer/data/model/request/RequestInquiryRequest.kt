package com.ozzy.bunqer.data.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 2.09.2021.
 */
data class RequestInquiryRequest(
    @SerializedName("amount_inquired")
    val amountInquired: AmountInquired? = null,
    @SerializedName("counterparty_alias")
    val counterpartyAlias: CounterpartyAlias? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("allow_bunqme")
    val allowBunqme: Boolean? = null
)

data class AmountInquired(
    @SerializedName("value")
    val value: String? = null,
    @SerializedName("currency")
    val currency: String? = null
)

data class CounterpartyAlias(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("value")
    val value: String? = null,
    @SerializedName("name")
    val name: String? = null
)