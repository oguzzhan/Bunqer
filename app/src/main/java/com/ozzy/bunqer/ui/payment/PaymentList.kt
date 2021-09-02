package com.ozzy.bunqer.ui.payment

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ozzy.bunqer.data.model.response.Payment

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
@ExperimentalFoundationApi
@Composable
fun PaymentList() {

    //payment paging list
    val viewModel: PaymentViewModel = hiltViewModel()
    val payments = viewModel.getPaymentList().collectAsLazyPagingItems()
    LazyColumn(
        Modifier.fillMaxWidth().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader() {
            SugarDaddyCaller {
                viewModel.callSugarDaddy()
            }
        }
        items(payments) { payment ->
            PaymentRow(payment?.payment)
        }
    }
    payments.refresh()
}

@Composable
fun PaymentRow(payment: Payment?) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(20.dp)
            .fillMaxWidth(),
    ) {
        ConstraintLayout {
            val (description, amount, id) = createRefs()
            Text(payment?.description ?: "Description", Modifier.constrainAs(description) {
                top.linkTo(parent.top, margin = 10.dp)
            })
            Text(payment?.id.toString(), Modifier.constrainAs(id) {
                top.linkTo(description.bottom, margin = 12.dp)
            })
        }
    }
}

@Composable
fun SugarDaddyCaller(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Call Your Sugar Daddy")
    }
}