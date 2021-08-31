package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.request.SessionRequest
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
class BunqerDataSource @Inject constructor(private val bunqerService: BunqerService) {

    //<editor-fold desc="App Setup">
    suspend fun createUser() = bunqerService.createUser()

    suspend fun installation(requestBody: InstallationRequest) =
        bunqerService.installation(requestBody)

    suspend fun registerDevice(requestBody: RegisterDeviceRequest) =

        bunqerService.registerDevice(requestBody)


    suspend fun startSession(requestBody: SessionRequest) =
        bunqerService.startSession(requestBody)

    suspend fun getMonetaryAccounts(userId: String) = bunqerService.getMonetaryAccounts(userId)
    //</editor-fold>

    //<editor-fold desc="Payment and Monetary Accounts">
    //</editor-fold>
}