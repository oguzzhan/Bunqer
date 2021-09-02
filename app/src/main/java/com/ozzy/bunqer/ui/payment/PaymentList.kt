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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ozzy.bunqer.R
import com.ozzy.bunqer.data.model.response.Payment
import com.ozzy.bunqer.ui.common.LoadingItem
import com.ozzy.bunqer.ui.common.LoadingView

/**
 * Created by Oğuzhan Karacan on 1.09.2021.
 */
@ExperimentalFoundationApi
@Composable
fun PaymentList() {

    val isRefreshing = remember { mutableStateOf(false) }

    //payment paging list
    val viewModel: PaymentViewModel = hiltViewModel()
    val payments = viewModel.getPaymentList().collectAsLazyPagingItems()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing.value),
        onRefresh = {
            payments.refresh()
        }
    ) {
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