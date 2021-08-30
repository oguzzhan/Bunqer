package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */

data class InstallationResponse(@SerializedName("Response") val itemList: ArrayList<InstallationResponseItem>)

data class InstallationResponseItem(
    @SerializedName("Id")
    var id: Id? = null,
    @SerializedName("Token")
    var token: Token? = null,
    @SerializedName("ServerPublicKey")
    var serverPublicKey: ServerPublicKey? = null
)

data class Id(
    @SerializedName("id")
    var id: Int? = null
)

data class Token(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("created")
    var created: String? = null,
    @SerializedName("updated")
    var updated: String? = null,
    @SerializedName("token")
    var token: String? = null
)

data class ServerPublicKey(
    @SerializedName("server_public_key")
    var serverPublicKey: String? = null
)