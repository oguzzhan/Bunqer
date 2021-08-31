package com.ozzy.bunqer.data

import android.util.Log
import androidx.annotation.MainThread
import com.google.gson.Gson
import com.ozzy.bunqer.data.model.BunqErrorResponse
import com.ozzy.bunqer.data.model.BunqResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
abstract class NetworkBoundRepository<RESULT> {

    fun asFlow() = flow<BunqResult<RESULT?>> {

        emit(BunqResult.loading())

        val apiResponse = fetchFromRemote()
        val remotePosts = apiResponse.body()

        if (apiResponse.isSuccessful && remotePosts != null) {
            emit(BunqResult.success(apiResponse.body()))

        } else if (apiResponse.errorBody() != null) {
            val errorResponse = Gson().fromJson(
                apiResponse.errorBody()!!.charStream(),
                BunqErrorResponse::class.java
            )

            emit(BunqResult.bunqError(errorResponse))
        }
    }.catch { e ->
        Log.d("Error", e.localizedMessage ?: "")
        emit(BunqResult.error())
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}