package com.ozzy.bunqer.data

import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.response.PaymentListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
class MoneyRepository @Inject constructor(private val dataSource: BunqerDataSource) {


    fun getPayments(userId: String, accountId: String): Flow<BunqResult<PaymentListResponse?>> {
        return object : NetworkBoundRepository<PaymentListResponse>() {
            override suspend fun fetchFromRemote(): Response<PaymentListResponse> {
                return dataSource.getPayments(userId, accountId)
            }
        }.asFlow()
    }
}