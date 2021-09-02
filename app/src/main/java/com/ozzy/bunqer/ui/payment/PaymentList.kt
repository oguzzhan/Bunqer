package com.ozzy.bunqer.ui.payment

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ozzy.bunqer.R
import com.ozzy.bunqer.data.model.response.Amount
import com.ozzy.bunqer.data.model.response.CounterpartyAlias
import com.ozzy.bunqer.data.model.response.Payment
import com.ozzy.bunqer.ui.common.LoadingItem
import com.ozzy.bunqer.ui.common.LoadingView

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PaymentList(
    navController: NavController
) {

    val isRefreshing = remember { mutableStateOf(false) }

    //payment paging list
    val viewModel: PaymentViewModel = hiltViewModel()
    val payments = viewModel.paymentList.collectAsLazyPagingItems()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing.value),
        onRefresh = {
            payments.refresh()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            Modifier.fillMaxWidth().padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    SugarDaddyCaller {
                        viewModel.callSugarDaddy()
                    }
                    Button({ payments.refresh() }) {
                        Text("Refresh")
                    }
                    Button({ navController.navigate("payment") }) {
                        Text("Make a payment")
                    }
                }
            }
            items(payments) { payment ->
                PaymentRow(
                    payment?.payment
                ) {
                    Log.d("messaage", "go to detail")
                }
            }

            payments.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.refresh is LoadState.Error -> {
                        item {
                            Button(onClick = { refresh() }) {
                                Text(text = "Tekrar Dene")
                            }
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Error -> {
                        item {
                            Button(onClick = { refresh() }) {
                                Text(text = stringResource(R.string.try_again))
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun PaymentRow(
    @PreviewParameter(PaymentPreviewParameterProvider::class) payment: Payment?,
    onClick: () -> Unit = { }
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(20.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {

        ConstraintLayout {
            val (description, amount, who) = createRefs()

            Text(payment?.getPersonWhoPaid() ?: "", Modifier.constrainAs(who) {
                top.linkTo(parent.top, margin = 10.dp)
            })
            Text("Description: ${payment?.description}", Modifier.constrainAs(description) {
                top.linkTo(who.bottom, margin = 10.dp)
            })
            Text("Amount: ${payment?.getPaymentDescription()}", Modifier.constrainAs(amount) {
                top.linkTo(description.bottom, margin = 10.dp)
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

class PaymentPreviewParameterProvider : PreviewParameterProvider<Payment> {
    override val values: Sequence<Payment>
        get() = sequenceOf(previewPayment)
}

val previewPayment = Payment(
    description = "Gimme Money!",
    amount = Amount("TRY", "250"),
    counterpartyAlias = CounterpartyAlias(
        iban = "NL32BUNQ2025313705",
        displayName = "Sugar Daddy <3"
    ),
    subType = "REQUEST"

)