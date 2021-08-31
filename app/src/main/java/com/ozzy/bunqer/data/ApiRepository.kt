package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.response.ApiKeyResponse
import com.ozzy.bunqer.data.model.response.InstallationResponse
import com.ozzy.bunqer.data.model.response.RegisterDeviceResponse
import com.ozzy.bunqer.util.extension.generatePublicKey
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

    fun installation(): Flow<BunqResult<InstallationResponse?>> {
        return object : NetworkBoundRepository<InstallationResponse>() {
            override suspend fun fetchFromRemote(): Response<InstallationResponse> {
                return dataSource.installation(InstallationRequest(generatePublicKey()))
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
}