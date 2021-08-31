package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.request.SessionRequest
import com.ozzy.bunqer.data.model.response.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
class ApiRepository @Inject constructor(private val dataSource: BunqerDataSource) {

    fun createUser(): Flow<BunqResult<ApiKeyResponse?>> {
        return object : NetworkBoundRepository<ApiKeyResponse>() {
            override suspend fun fetchFromRemote(): Response<ApiKeyResponse> {
                return dataSource.createUser()
            }
        }.asFlow()
    }

    fun installation(key: String): Flow<BunqResult<InstallationResponse?>> {
        return object : NetworkBoundRepository<InstallationResponse>() {
            override suspend fun fetchFromRemote(): Response<InstallationResponse> {
                return dataSource.installation(InstallationRequest(key))
            }
        }.asFlow()
    }

    fun registerDevice(requestBody: RegisterDeviceRequest): Flow<BunqResult<RegisterDeviceResponse?>> {
        return object : NetworkBoundRepository<RegisterDeviceResponse>() {
            override suspend fun fetchFromRemote(): Response<RegisterDeviceResponse> {
                return dataSource.registerDevice(requestBody)
            }

        }.asFlow()
    }

    fun startSession(requestBody: SessionRequest): Flow<BunqResult<SessionResponse?>> {
        return object : NetworkBoundRepository<SessionResponse>() {
            override suspend fun fetchFromRemote(): Response<SessionResponse> {
                return dataSource.startSession(requestBody)
            }
        }.asFlow()
    }

    fun getMonetaryAccounts(userId: String): Flow<BunqResult<MonetaryAccountResponse?>> {
        return object : NetworkBoundRepository<MonetaryAccountResponse>() {
            override suspend fun fetchFromRemote(): Response<MonetaryAccountResponse> {
                return dataSource.getMonetaryAccounts(userId)
            }
        }.asFlow()
    }
}