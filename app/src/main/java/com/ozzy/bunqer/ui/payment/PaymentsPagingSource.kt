package com.ozzy.bunqer.ui.payment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ozzy.bunqer.data.BunqerService
import com.ozzy.bunqer.data.model.response.PaymentResponse

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
class PaymentsPagingSource(
    private val userId: String,
    private val accountId: String,
    private val bunqerService: BunqerService
) :
    PagingSource<String, PaymentResponse>() {

    override fun getRefreshKey(state: PagingState<String, PaymentResponse>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PaymentResponse> {
        return try {
            val response = bunqerService.getPayments(userId, accountId)
            LoadResult.Page(
                data = response.body()?.response!!,
                prevKey = response.body()?.pagination?.olderUrl,
                nextKey = response.body()?.pagination?.newerUrl
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}