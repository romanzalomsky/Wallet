package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.domain.model.AccountType.*
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.ScreenTopBar
import com.zalomsky.wallet.presentation.accounts.AccountDetailViewModel
import com.zalomsky.wallet.presentation.accounts.AccountUiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccount(
    onBackPressed: () -> Unit,
    state: Any?
){
    val viewModel : AccountDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val account = viewModel.account.observeAsState().value

    Scaffold(
        topBar = {
            ScreenTopBar(
                appHeader = "Add Account",
                onDone = {viewModel.addAccount(onBackPressed)},
                onBackPressed = onBackPressed
            )
        },
    ) {
        AddView(
            state = state as String,
            onNameChange = viewModel::onNameChange,
            onDescriptionChange = viewModel::onDescriptionChange,
            onBalanceChange = viewModel::onBalanceChange,
            onTargetChange = viewModel::onTargetChange,
            uiState = uiState
        )
    }
}

@Composable
fun AddView(
    state: String,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit,
    uiState: AccountUiState
) {
    when(state){
        REGULAR -> {
            RegularAccountView(
                uiState = uiState,
                onNameChange = onNameChange,
                onDescriptionChange = onDescriptionChange,
                onBalanceChange = onBalanceChange
            )
        }
        SAVING -> {
            SavingAccountView(
                uiState = uiState,
                onNameChange = onNameChange,
                onDescriptionChange = onDescriptionChange,
                onBalanceChange = onBalanceChange,
                onTargetChange = onTargetChange
            )
        }
        DEBT -> {
            DebtAccountView(
                uiState = uiState,
                onNameChange = onNameChange,
                onDescriptionChange = onDescriptionChange,
                onBalanceChange = onBalanceChange
            )
        }
    }
}
