package com.ozzy.bunqer.ui.main

import androidx.lifecycle.ViewModel
import com.ozzy.bunqer.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    fun installation() {
        apiRepository.installation()
    }

    val installation = apiRepository.installation()
}