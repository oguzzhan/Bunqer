package com.ozzy.bunqer.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozzy.bunqer.ui.payment.list.PaymentList
import com.ozzy.bunqer.ui.payment.list.PaymentViewModel
import com.ozzy.bunqer.ui.payment.new.NewPayment
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by Oğuzhan Karacan on 2.09.2021.
 */
@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun BunqerNavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            val viewModel = hiltViewModel<PaymentViewModel>()
            PaymentList(navController, viewModel)
        }
        composable(
            route = "payment"
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<PaymentViewModel>()
            NewPayment(navController, viewModel)
        }

    }
}