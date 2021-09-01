package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.request.SessionRequest
import com.ozzy.bunqer.data.model.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @POST("session-server")
    suspend fun startSession(@Body requestBody: SessionRequest): Response<SessionResponse>

    @GET("user/{user_id}/monetary-account")
    suspend fun getMonetaryAccounts(
        @Path("user_id") userId: String
    ): Response<MonetaryAccountResponse>

    @GET("user/{user_id}/monetary-account/{monetary_account_id}/payment")
    suspend fun getPayments(
        @Path("user_id") userId: String,
        @Path("monetary_account_id") monetaryAccountId: String
    ): Response<PaymentListResponse>
}