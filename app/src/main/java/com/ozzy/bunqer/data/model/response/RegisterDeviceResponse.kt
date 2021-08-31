package com.ozzy.bunqer.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
data class RegisterDeviceResponse(
    @SerializedName("Response") val idList: ArrayList<Id>
)