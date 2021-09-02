package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 2.09.2021.
 */
data class RequestInquiryResponse(
    @SerializedName("Response")
    val response: List<Response>? = null
)

data class RequestInquiryResponseItem(
    @SerializedName("Id")
    val id: Id? = null
)