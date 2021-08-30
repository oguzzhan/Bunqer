package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.response.InstallationResponse
import com.ozzy.bunqer.util.extension.generatePublicKey
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
class ApiRepository @Inject constructor(private val dataSource: BunqerDataSource) {

    fun installation(): Flow<BunqResult<InstallationResponse?>> {
        return object : NetworkBoundRepository<InstallationResponse>() {
            override suspend fun fetchFromRemote(): Response<InstallationResponse> {
                return dataSource.installation(InstallationRequest(generatePublicKey()))
            }
        }.asFlow()
    }
}