package com.ozzy.bunqer.ui.payment.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ozzy.bunqer.data.BunqerService
import com.ozzy.bunqer.data.MoneyRepository
import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.AmountInquired
import com.ozzy.bunqer.data.model.request.CounterpartyAlias
import com.ozzy.bunqer.data.model.request.RequestInquiryRequest
import com.ozzy.bunqer.di.BunqPreferences
import com.ozzy.bunqer.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
@HiltViewModel
class PaymentListViewModel @Inject constructor(
    private val moneyRepository: MoneyRepository,
    private val bunqPreferences: BunqPreferences,
    private val bunqerService: BunqerService
) : ViewModel() {

    fun callSugarDaddy() {
        val requestInquiryRequest = RequestInquiryRequest(
            amountInquired = AmountInquired(
                value = "100",
                currency = "EUR"
            ),
            counterpartyAlias = CounterpartyAlias(
                type = "EMAIL",
                value = "sugardaddy@bunq.com",
                name = "Sugar Daddy"
            ),
            description = "Gimme money!",
            allowBunqme = false
        )


        viewModelScope.launch {
            moneyRepository.requestInquiry(
                bunqPreferences.getString(Constants.Preferences.USER_ID),
                bunqPreferences.getString(Constants.Preferences.MONETARY_ACCOUNT_ID),
                requestInquiryRequest
            ).collect {
                when (it) {
                    is BunqResult.BunqError -> {
                        Log.d("RequestMoney", "Error")
                    }
                    is BunqResult.BunqResponse -> {
                        Log.d("RequestMoney", "Error")
                    }
                    is BunqResult.Error -> {
                        Log.d("RequestMoney", "Error")
                    }
                    is BunqResult.Loading -> {
                        Log.d("RequestMoney", "Loading")
                    }
                }
            }
        }
    }

    val paymentList = Pager(
        PagingConfig(pageSize = 10)
    ) {
        PaymentsPagingSource(
            bunqPreferences.getString(Constants.Preferences.USER_ID),
            bunqPreferences.getString(Constants.Preferences.MONETARY_ACCOUNT_ID),
            bunqerService
        )
    }.flow.cachedIn(viewModelScope)

}