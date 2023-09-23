package com.zalomsky.wallet.presentation.accounts.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.presentation.accounts.WalletIconButton
import com.zalomsky.wallet.presentation.accounts.add.BalanceInputFields
import com.zalomsky.wallet.presentation.accounts.add.DescriptionInputFields
import com.zalomsky.wallet.presentation.accounts.add.NameInputFields
import com.zalomsky.wallet.presentation.accounts.add.TargetInputField
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccountScreen(
    onBackPressed: () -> Unit,
    state: String?,
    id: String?
) {
    val viewModel: EditAccountScreenViewModel = hiltViewModel()
    val account = viewModel.account.observeAsState().value
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = id, block = { id?.toLong()?.let { viewModel.getAccountById(it) } })

    Scaffold(
        topBar = {
            EditAccountAppBar(
                onUpdateAccount = { viewModel.updateAccount(onBackPressed) },
                upPress = onBackPressed
            )
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        account?.let { account ->
            EditAccountView(
                state = state,
                account = account,
                onNameChange = viewModel::onNameChange,
                onDescriptionChange = viewModel::onDescriptionChange,
                onBalanceChange = viewModel::onBalanceChange,
                onTargetChange = viewModel::onTargetChange,
                onDeleteAccount = { viewModel.deleteAccounts(onBackPressed) }
            )
        }
    }
}

@Composable
fun EditAccountAppBar(
    onUpdateAccount: () -> Unit,
    upPress: () -> Unit
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
            text = "Edit Account",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        WalletIconButton(
            icon = Icons.Outlined.Check,
            description = "check icon",
            onClick = onUpdateAccount
        )
    }
}

@Composable
fun EditAccountView(
    state: String?,
    account: Account,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit,
    onDeleteAccount: () -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        NameInputFields(
            labelText = stringResource(id = R.string.name_label),
            value = account.name,
            onNewValue = onNameChange
        )
        DescriptionInputFields(
            labelText = stringResource(id = R.string.description_label),
            value = account.description,
            onNewValue = onDescriptionChange
        )
        BalanceInputFields(
            labelText = stringResource(id = R.string.balance_label),
            value = account.balance,
            onNewValue = onBalanceChange
        )
        account.iconColor = listOfColors.random()
        account.icon = listOfAccountsIcons.random()
        account.type = state.toString()
        if (state == AccountType.SAVING) {
            TargetInputField(
                labelText = stringResource(id = R.string.target_label),
                value = account.target,
                onNewValue = onTargetChange
            )
        }
        ButtonDelete(
            onDeleteAccount = onDeleteAccount
        )
    }
}

@Composable
fun ButtonDelete(
    onDeleteAccount: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Red,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(56.dp)
            .clickable(onClick = onDeleteAccount)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "",
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.delete_account_button),
                fontSize = 15.sp,
                fontFamily = aksharMedium,
                color = Color.White
            )
        }
    }
}