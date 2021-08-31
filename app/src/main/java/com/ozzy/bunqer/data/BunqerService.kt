package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.response.ApiKeyResponse
import com.ozzy.bunqer.data.model.response.InstallationResponse
import com.ozzy.bunqer.data.model.response.RegisterDeviceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
interface BunqerService {

    @POST("sandbox-user-person")
    suspend fun createUser(): Response<ApiKeyResponse>

    @POST("installation")
    suspend fun installation(@Body requestBody: InstallationRequest): Response<InstallationResponse>

    @POST("device-server")
    suspend fun registerDevice(@Body requestBody: RegisterDeviceRequest): Response<RegisterDeviceResponse>
}