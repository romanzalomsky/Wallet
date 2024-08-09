package com.zalomsky.wallet.features.accounts.update

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.features.accounts.screen.AccountUiState
import com.zalomsky.wallet.features.common.color.backgroundColor
import com.zalomsky.wallet.features.common.components.ButtonDelete
import com.zalomsky.wallet.features.common.components.WalletAppBar
import com.zalomsky.wallet.features.common.components.WalletDoubleInputField
import com.zalomsky.wallet.features.common.components.WalletStringInputField
import com.zalomsky.wallet.features.common.components.horizontalPaddingSize

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun EditAccountScreen(
    onBackPressed: () -> Unit,
    state: String?,
    id: Long
) {
    val viewModel: EditAccountViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val account = viewModel.uiState.value.accountEntity

    LaunchedEffect(id) {
        viewModel.onEvent(AccountEvent.Load(id))
    }

    Scaffold(
        topBar = {
            WalletAppBar(
                text = stringResource(id = R.string.edit_account_header),
                onClick = { viewModel.onEvent(AccountEvent.Update(onBackPressed)) },
                upPress = onBackPressed,
                navIcon = Icons.Outlined.ArrowBack
            )
        },
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        UpdateAccountView(
            state = state,
            uiState = uiState,
            onNameChange = viewModel::onNameChange,
            onDescriptionChange = viewModel::onDescriptionChange,
            onBalanceChange = viewModel::onBalanceChange,
            onTargetChange = viewModel::onTargetChange,
            onDeleteAccount = { viewModel.deleteAccounts(account, onBackPressed) }
        )
    }
}

@Composable
fun UpdateAccountView(
    state: String?,
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit,
    onDeleteAccount: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontalPaddingSize),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        WalletStringInputField(
            labelText = stringResource(id = R.string.name_label),
            value = uiState.accountEntity.name,
            onNewValue = onNameChange
        )
        WalletStringInputField(
            labelText = stringResource(id = R.string.description_label),
            value = uiState.accountEntity.description,
            onNewValue = onDescriptionChange
        )
        WalletDoubleInputField(
            value = uiState.accountEntity.balance,
            onNewValue = onBalanceChange
        )
        when (state) {
            AccountType.SAVING.name -> {
                WalletDoubleInputField(
                    value = uiState.accountEntity.target,
                    onNewValue = onTargetChange
                )
            }
        }
        ButtonDelete(onDeleteAccount = onDeleteAccount)
    }
}
