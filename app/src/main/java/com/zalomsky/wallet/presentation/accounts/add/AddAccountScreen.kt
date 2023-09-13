package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountType.*
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.accounts.AddAccountAppBar
import com.zalomsky.wallet.presentation.common.color.backgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    onBackPressed: () -> Unit,
    state: Any?
){
    val viewModel : AddAccountScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AddAccountAppBar(upPress = onBackPressed, addAccount = {viewModel.addAccount(onBackPressed)})
        }
    ) {
        when(state){
            REGULAR -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
            SAVING -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
            DEBT -> {
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
    state: Any?,
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.account.name,
                onNewValue = onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = uiState.account.description,
                onNewValue = onDescriptionChange
            )
            BalanceInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = uiState.account.balance,
                onNewValue = onBalanceChange
            )
            if(state == SAVING){
                TargetInputField(
                    labelText = stringResource(id = R.string.target_label),
                    value = uiState.account.target,
                    onNewValue = onTargetChange
                )
            }
            uiState.account.type = state.toString()
        }
    }
}
