package com.ozzy.bunqer.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozzy.bunqer.ui.payment.PaymentList
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
        composable("list") { PaymentList(navController) }
        composable(
            route = "payment"
        ) { navBackStackEntry ->
            //
        }

    }
}