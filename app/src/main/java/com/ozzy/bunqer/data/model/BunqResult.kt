package com.ozzy.bunqer.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
sealed class BunqResult<T> {
    class Loading<T> : BunqResult<T>()

    data class BunqResponse<T>(@SerializedName("Response") val response: T) : BunqResult<T>()

    data class BunqError<T>(val bunqErrorResponse: BunqErrorResponse) : BunqResult<T>()

    data class Error<T>(val errorMessage: String) : BunqResult<T>()

    companion object {

        fun <T> loading() = Loading<T>()

        fun <T> success(data: T) =
            BunqResponse(data)

        fun <T> bunqError(bunqError: BunqErrorResponse) = BunqError<T>(bunqError)

        fun <T> error() = Error<T>("Error")
    }

}

data class BunqErrorResponse(@SerializedName("Error") val bunqError: List<BunqErrorModel>?)

data class BunqErrorModel(
    @SerializedName("error_description") val errorDescription: String? = null,
    @SerializedName("error_description_translated") val errorDescriptionTranslated: String? = null
)
