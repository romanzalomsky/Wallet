package com.zalomsky.wallet.presentation.accounts.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.ScreenTopBar
import com.zalomsky.wallet.presentation.accounts.AccountViewModel
import com.zalomsky.wallet.presentation.accounts.add.BalanceInputFields
import com.zalomsky.wallet.presentation.accounts.add.DescriptionInputFields
import com.zalomsky.wallet.presentation.accounts.add.NameInputFields
import com.zalomsky.wallet.presentation.common.color.backgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccount(
    onBackPressed: () -> Unit,
    id: String?,
){
    val viewModel: AccountViewModel = hiltViewModel()
    val account = viewModel.account.observeAsState().value

    id?.toLong()?.let {
        viewModel.getAccountById(id = it)
    }

    Scaffold(
        topBar = {
            ScreenTopBar(
                appHeader = "Edit Account",
                onDone = {viewModel.updateAccount(onBackPressed)},
                onBackPressed = onBackPressed
            )
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        EditView(
            account = account,
            onNameChange = viewModel::onNameChange,
            onDescriptionChange = viewModel::onDescriptionChange,
            onBalanceChange = viewModel::onBalanceChange,
            onAccountDelete = {viewModel.deleteAccount(onBackPressed)}
        )
    }
}

@Composable
fun EditView(
    account: Account?,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onAccountDelete: () -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        account?.let {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = it.name,
                onNewValue = onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = it.description,
                onNewValue = onDescriptionChange
            )
            BalanceInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = it.balance,
                onNewValue = onBalanceChange
            )
        }
        ButtonDelete(onAccountDelete = onAccountDelete)
    }
}