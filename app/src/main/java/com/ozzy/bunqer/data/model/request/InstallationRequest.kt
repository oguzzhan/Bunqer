package com.ozzy.bunqer.data.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
data class InstallationRequest(
    @SerializedName("client_public_key") var publicKey: String
)
