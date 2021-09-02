package com.ozzy.bunqer.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ozzy.bunqer.data.BunqerService
import com.ozzy.bunqer.data.MoneyRepository
import com.ozzy.bunqer.data.model.response.PaymentResponse
import com.ozzy.bunqer.di.BunqPreferences
import com.ozzy.bunqer.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val moneyRepository: MoneyRepository,
    private val bunqPreferences: BunqPreferences,
    private val bunqerService: BunqerService
) : ViewModel() {


    fun getPaymentList(): Flow<PagingData<PaymentResponse>> {
        val source = PaymentsPagingSource(
            bunqPreferences.getString(Constants.Preferences.USER_ID),
            bunqPreferences.getString(Constants.Preferences.MONETARY_ACCOUNT_ID),
            bunqerService
        )

        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            source
        }.flow.cachedIn(viewModelScope)
    }

}