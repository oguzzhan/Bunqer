package com.ozzy.bunqer.data.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
data class SessionRequest(
    @SerializedName("secret") val secret: String,
)
