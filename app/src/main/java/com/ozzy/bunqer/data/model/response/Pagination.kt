package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("future_url")
    val futureUrl: String? = null,
    @SerializedName("newer_url")
    val newerUrl: String? = null,
    @SerializedName("older_url")
    val olderUrl: String? = null
)