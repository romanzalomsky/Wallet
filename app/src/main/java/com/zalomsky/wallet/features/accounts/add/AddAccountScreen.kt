package com.zalomsky.wallet.features.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
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
import com.zalomsky.wallet.features.common.components.WalletAppBar
import com.zalomsky.wallet.features.common.components.WalletDoubleInputField
import com.zalomsky.wallet.features.common.components.WalletStringInputField
import com.zalomsky.wallet.features.common.components.horizontalPaddingSize

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    onBackPressed: () -> Unit,
    state: String?
) {
    val viewModel: AddAccountViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            WalletAppBar(
                text = stringResource(id = R.string.add_account_header),
                upPress = onBackPressed,
                onClick = { viewModel.addAccount(onBackPressed) },
                navIcon = Icons.Outlined.ArrowBack
            )
        }
    ) {
        when (state) {
            AccountType.REGULAR.name -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
            AccountType.SAVING.name -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
            AccountType.DEBT.name -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountView(
    state: String?,
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontalPaddingSize)
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
            uiState.accountEntity.type = state.toString()
        }
    }
}

