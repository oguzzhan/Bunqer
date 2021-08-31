package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
data class ApiKeyResponse(
    @SerializedName("Response") val apiKeyList: List<ApiKeyObject>? = null

) {
    fun getApiKey(): String {
        return apiKeyList?.firstOrNull()?.apiKey?.apiKey ?: ""
    }
}

data class ApiKeyObject(
    @SerializedName("ApiKey")
    val apiKey: ApiKey? = null
)

data class ApiKey(
    @SerializedName("api_key")
    val apiKey: String? = null
)
