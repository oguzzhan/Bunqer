package com.ozzy.bunqer.data.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
data class RegisterDeviceRequest(
    @SerializedName("description") val description: String,
    @SerializedName("secret") val secret: String,
)
