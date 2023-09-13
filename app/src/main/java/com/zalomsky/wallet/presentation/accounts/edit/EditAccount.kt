package com.zalomsky.wallet.presentation.accounts.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.presentation.ScreenTopBar
import com.zalomsky.wallet.presentation.accounts.AccountDetails
import com.zalomsky.wallet.presentation.accounts.AccountScreenViewModel
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccount(
    onBackPressed: () -> Unit,
    state: Any?,
    id: String?,
){
    val viewModel: AccountScreenViewModel = hiltViewModel()
/*    val uiState by viewModel.uiState.collectAsStateWithLifecycle()*/
    val coroutineScope = rememberCoroutineScope()

/*    val account = viewModel.account.observeAsState().value*/
    id?.toLong()?.let { viewModel.getAccountById(id = it) }

    Scaffold(
        topBar = {
            ScreenTopBar(
                appHeader = "Edit Account",
                onDone = {
                    coroutineScope.launch {
                        viewModel.updateAccount(onBackPressed)
                    }
                },
                onBackPressed = onBackPressed
            )
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        EditAccountView(
            state = state.toString(),
            accountUiState = viewModel.accountUiState,
            onNewValue = viewModel::updateUiState
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccountView(
    state: Any?,
    accountUiState: AccountUiState,
    onNewValue: (AccountDetails) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
/*        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {
            InputFields(
                accountDetails = accountUiState.accountDetails,
                onNewValue = onNewValue
            )
            accountUiState.accountDetails.type = state.toString()
        }*/
    }
}

