package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountType.*
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.accounts.WalletIconButton
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    onBackPressed: () -> Unit,
    state: String?
) {
    val viewModel: AddAccountScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AddAccountAppBar(
                text = "Add Account",
                upPress = onBackPressed,
                addAccount = { viewModel.addAccount(onBackPressed) }
            )
        }
    ) {
        when (state) {
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

@Composable
fun AddAccountAppBar(
    text: String,
    upPress: () -> Unit,
    addAccount: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ) {
        WalletIconButton(
            icon = Icons.Outlined.ArrowBack,
            description = "arrow back icon",
            onClick = upPress
        )
        Text(
            text = text,
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        WalletIconButton(
            icon = Icons.Outlined.Check,
            description = "check icon",
            onClick = addAccount
        )
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
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(15.dp)
            .background(backgroundColor),
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
        if (state == SAVING) {
            TargetInputField(
                labelText = stringResource(id = R.string.target_label),
                value = uiState.account.target,
                onNewValue = onTargetChange
            )
        }
        uiState.account.type = state.toString()
    }
}
