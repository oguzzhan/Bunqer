package com.ozzy.bunqer.ui.payment

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
@Composable
fun PaymentList() {
    val viewModel: PaymentViewModel = hiltViewModel()
    val payments = viewModel.getPaymentList().collectAsLazyPagingItems()
    LazyColumn {
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
            val (description, amount) = createRefs()
            Text(payment?.description ?: "Description")
        }
    }
}