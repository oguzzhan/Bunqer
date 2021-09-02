package com.ozzy.bunqer.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozzy.bunqer.data.ApiRepository
import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.data.model.request.SessionRequest
import com.ozzy.bunqer.di.BunqPreferences
import com.ozzy.bunqer.util.Constants
import com.ozzy.bunqer.util.extension.generatePublicKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val bunqPreferences: BunqPreferences
) : ViewModel() {

    val shouldFetchList = MutableLiveData<Boolean>().apply { value = false }

    fun createUser() {
        if (bunqPreferences.getString(Constants.Preferences.API_KEY).isEmpty()) {
            viewModelScope.launch {
                apiRepository.createUser().collect {
                    when (it) {
                        is BunqResult.BunqError -> {

                        }
                        is BunqResult.BunqResponse -> {
                            Log.d("apiKey", it.response?.getApiKey() ?: "")
                            bunqPreferences.putString(
                                Constants.Preferences.API_KEY,
                                it.response?.getApiKey() ?: ""
                            )
                            installation()
                        }
                        is BunqResult.Error -> {

                        }
                        is BunqResult.Loading -> {

                        }
                    }
                }
            }
        } else {
            viewModelScope.launch {
                installation()
            }
        }

    }

    private suspend fun installation() {
        if (bunqPreferences.getString(Constants.Preferences.SESSION_TOKEN).isEmpty()) {
            val key = generatePublicKey()
            bunqPreferences.putString(Constants.Preferences.PUBLIC_KEY, key)
            apiRepository.installation(key).collect {
                when (it) {
                    is BunqResult.BunqError -> {
                        Log.d("test", "text")

                    }
                    is BunqResult.BunqResponse -> {
                        it.response?.getToken()?.let { token ->
                            bunqPreferences.putString(
                                Constants.Preferences.SESSION_TOKEN,
                                token
                            )
                            registerDevice()
                        }
                    }
                    is BunqResult.Error -> {
                        Log.d("test", "text")

                    }
                    is BunqResult.Loading -> {
                        Log.d("test", "text")

                    }
                }
            }
        } else {
            shouldFetchList.value = true
        }

    }

    private suspend fun registerDevice() {
        val requestBody = RegisterDeviceRequest(
            description = "bunqerOzzy",
            secret = bunqPreferences.getString(Constants.Preferences.API_KEY)
        )
        apiRepository.registerDevice(requestBody).collect {
            when (it) {
                is BunqResult.BunqError -> {
                    Log.d("test", "text")
                }
                is BunqResult.BunqResponse -> {
                    startSession()
                }
                is BunqResult.Error -> {
                    Log.d("test", "text")
                }
                is BunqResult.Loading -> {
                    Log.d("test", "text")
                }
            }
        }
    }

    private suspend fun startSession() {
        val requestBody = SessionRequest(
            secret = bunqPreferences.getString(Constants.Preferences.API_KEY)
        )

        apiRepository.startSession(requestBody).collect {
            when (it) {
                is BunqResult.BunqError -> {
                    Log.d("StartSession", "Error")
                }
                is BunqResult.BunqResponse -> {
                    it.response?.getToken()?.let { token ->
                        bunqPreferences.putString(
                            Constants.Preferences.SESSION_TOKEN,
                            token
                        )
                    }
                    bunqPreferences.putString(
                        Constants.Preferences.USER_ID,
                        it.response?.getUserId() ?: ""
                    )

                    getMonetaryAccounts()
                }
                is BunqResult.Error -> {
                    Log.d("StartSession", "Error")
                }
                is BunqResult.Loading -> {
                    Log.d("StartSession", "Loading")
                }
            }
        }
    }

    private suspend fun getMonetaryAccounts() {
        apiRepository.getMonetaryAccounts(bunqPreferences.getString(Constants.Preferences.USER_ID))
            .collect {
                when (it) {
                    is BunqResult.BunqError -> {
                        Log.d("GetMonetaryAccount", "Error")
                    }
                    is BunqResult.BunqResponse -> {
                        bunqPreferences.putString(
                            Constants.Preferences.MONETARY_ACCOUNT_ID,
                            it.response?.getFirstAccountID() ?: ""
                        )
                        shouldFetchList.value = true
                    }
                    is BunqResult.Error -> {
                        Log.d("GetMonetaryAccount", "Error")
                    }
                    is BunqResult.Loading -> {
                        Log.d("GetMonetaryAccount", "Loading")
                    }
                }
            }
    }

}