package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.RequestInquiryRequest
import com.ozzy.bunqer.data.model.response.RequestInquiryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
class MoneyRepository @Inject constructor(private val dataSource: BunqerDataSource) {

    fun requestInquiry(
        userId: String,
        accountId: String,
        requestInquiryRequest: RequestInquiryRequest
    ): Flow<BunqResult<RequestInquiryResponse?>> {
        return object : NetworkBoundRepository<RequestInquiryResponse>() {
            override suspend fun fetchFromRemote(): Response<RequestInquiryResponse> {
                return dataSource.requestInquiry(
                    userId,
                    accountId,
                    requestInquiryRequest
                )
            }

        }.asFlow()
    }
}