package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.zalomsky.wallet.presentation.common.icons.cardIcon
import com.zalomsky.wallet.presentation.listOfColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    onBackPressed: () -> Unit,
    state: String?
) {
    val viewModel: AddAccountScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dynamicIcon: Int = cardIcon

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
                    onTargetChange = viewModel::onTargetChange,
                    dynamicIcon = dynamicIcon
                )
            }

            SAVING -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange,
                    dynamicIcon = dynamicIcon
                )
            }

            DEBT -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange,
                    dynamicIcon = dynamicIcon
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
    dynamicIcon: Int,
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit
) {
    val showAlertDialog = remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(15.dp)
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Icon",
                    fontSize = 16.sp,
                    color = systemTextColor,
                    fontFamily = splineSansMedium,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                )
                Icon(
                    painter = painterResource(id = dynamicIcon),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
                WalletIconButton(
                    icon = Icons.Outlined.KeyboardArrowDown,
                    description = "",
                    onClick = {showAlertDialog.value = true}
                )
            }
            if(showAlertDialog.value){
                AccountIconAlertDialog(
                    showDialog = showAlertDialog.value,
                    onDismiss = { showAlertDialog.value = false },
                    onIconAdd = {  }
                )
            }
            ColorChoice(color = listOfColors.random(), dynamicIcon = dynamicIcon)
            uiState.account.type = state.toString()
        }
    }
}