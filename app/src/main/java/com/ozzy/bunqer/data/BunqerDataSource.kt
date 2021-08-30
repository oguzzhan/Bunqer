package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.request.InstallationRequest
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
class BunqerDataSource @Inject constructor(private val bunqerService: BunqerService) {
    suspend fun installation(requestBody: InstallationRequest) =
        bunqerService.installation(requestBody)

}