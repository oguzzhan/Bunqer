package com.ozzy.bunqer.ui.payment.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.ozzy.bunqer.ui.payment.list.PaymentViewModel

/**
 * Created by Oğuzhan Karacan on 2.09.2021.
 */
@Composable
fun NewPayment(navController: NavController, viewModel: PaymentViewModel) {
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

        var amount by remember { mutableStateOf("") }


        Text("Be a Sugar Daddy")

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = amount,
            onValueChange = {
                amount = it
            },
            label = { Text("Set the amount") }
        )

        Button(
            onClick = {
                viewModel.makePayment(amount) {
                    navController.popBackStack()
                }
            }
        ) {
            Text("Send Your Money")
        }


    }
}