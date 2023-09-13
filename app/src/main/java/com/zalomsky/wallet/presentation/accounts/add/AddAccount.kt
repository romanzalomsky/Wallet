package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.domain.model.AccountType.*
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.ScreenTopBar
import com.zalomsky.wallet.presentation.accounts.AccountDetails
import com.zalomsky.wallet.presentation.accounts.AccountScreenViewModel
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.accounts.RegularAccountView
import com.zalomsky.wallet.presentation.accounts.SavingAccountView
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccount(
    onBackPressed: () -> Unit,
    state: Any?
){
    val viewModel : AccountScreenViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ScreenTopBar(
                appHeader = "Add Account",
                onDone = {
                    coroutineScope.launch {
                        viewModel.saveAccount(onBackPressed)
                    }
                },
                onBackPressed = onBackPressed
            )
        },
    ) {
        AddAccountView(
            state = state as String,
            accountUiState = viewModel.accountUiState,
            onNewValue = viewModel::updateUiState
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountView(
    state: Any?,
    accountUiState: AccountUiState,
    onNewValue: (AccountDetails) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {
            when(state){
                REGULAR -> {
                    RegularAccountView(
                        accountDetails = accountUiState.accountDetails,
                        onNewValue = onNewValue
                    )
                }
                SAVING -> {
                    SavingAccountView(
                        accountDetails = accountUiState.accountDetails,
                        onNewValue = onNewValue
                    )
                }
                DEBT -> {
                    RegularAccountView(
                        accountDetails = accountUiState.accountDetails,
                        onNewValue = onNewValue
                    )
                }
            }
            accountUiState.accountDetails.type = state.toString()
        }
    }
}
