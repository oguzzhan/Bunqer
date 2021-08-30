package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.response.InstallationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
interface BunqerService {
    @POST("installation")
    suspend fun installation(@Body requestBody: InstallationRequest): Response<InstallationResponse>
}