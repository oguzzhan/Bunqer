package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.request.InstallationRequest
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.request.RequestInquiryRequest
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

    //<editor-fold desc="Payment">
    suspend fun requestInquiry(
        userId: String,
        accountId: String,
        requestBody: RequestInquiryRequest
    ) =
        bunqerService.requestInquiry(userId, accountId, requestBody)

    suspend fun makePayment(
        userId: String,
        accountId: String,
        requestBody: RequestInquiryRequest
    ) = bunqerService.makePayment(userId, accountId, requestBody)
    //</editor-fold>
}