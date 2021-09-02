package com.ozzy.bunqer.ui.payment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ozzy.bunqer.data.BunqerService
import com.ozzy.bunqer.data.model.response.PaymentResponse
import com.ozzy.bunqer.util.extension.getOlderId

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
class PaymentsPagingSource(
    private val userId: String,
    private val accountId: String,
    private val bunqerService: BunqerService
) :
    PagingSource<String, PaymentResponse>() {

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<String, PaymentResponse>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PaymentResponse> {
        return try {
            val next = params.key
            val response = bunqerService.getPayments(userId, accountId, next)
            LoadResult.Page(
                data = response.body()?.response!!,
                nextKey = response.body()?.pagination?.olderUrl?.getOlderId(),
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}