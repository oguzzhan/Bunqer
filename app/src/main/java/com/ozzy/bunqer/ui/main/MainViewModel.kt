package com.ozzy.bunqer.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozzy.bunqer.data.ApiRepository
import com.ozzy.bunqer.data.model.BunqResult
import com.ozzy.bunqer.data.model.request.RegisterDeviceRequest
import com.ozzy.bunqer.di.BunqPreferences
import com.ozzy.bunqer.util.Constants
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

    fun createUser() {
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
    }

    suspend fun installation() {
        apiRepository.installation().collect {
            when (it) {
                is BunqResult.BunqError -> {
                    Log.d("zaaa", "text")

                }
                is BunqResult.BunqResponse -> {
                    it.response?.getToken()?.let { token ->
                        bunqPreferences.putString(
                            Constants.Preferences.INSTALLATION_TOKEN,
                            token
                        )
                        registerDevice()
                    }
                }
                is BunqResult.Error -> {
                    Log.d("zaaa", "text")

                }
                is BunqResult.Loading -> {
                    Log.d("zaaa", "text")

                }
            }
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
                    Log.d("zaaa", "text")
                }
                is BunqResult.BunqResponse -> {
                    Log.d("zaaa", "text")
                }
                is BunqResult.Error -> {
                    Log.d("zaaa", "text")
                }
                is BunqResult.Loading -> {
                    Log.d("zaaa", "text")
                }
            }
        }
    }

}