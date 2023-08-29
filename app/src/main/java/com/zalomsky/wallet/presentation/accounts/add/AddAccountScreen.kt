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
import com.zalomsky.wallet.presentation.accounts.AccountUiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    upPress: () -> Unit
){
    val viewModel : AddAccountScreenViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddAccountView(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onBalanceChange = viewModel::onBalanceChange,
        upPress = upPress
    )

    /*Scaffold(
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AddAccountsHeader()
            NameInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = balance,
                onNewValue = { balance = it }
            )
            Card(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Row {
                        LazyRow(
                            userScrollEnabled = true
                        ){
                            items(listOfAccountsIcons){ icons ->
                                Icon(
                                    painter = painterResource(id = icons),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            icon = icons.toString()
                                        }
                                )
                            }
                        }
                    }
                    Row {
                        LazyRow(
                            userScrollEnabled = true
                        ){
                            items(listOfColors){ colors ->
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .graphicsLayer {
                                            clip = true
                                            shape = CircleShape
                                        }
                                        .background(Color(colors))
                                        .clickable {
                                            iconColor = colors.toString()
                                        }
                                )
                            }
                        }
                    }
                    ConfirmButton(
                        onClick = {
                            viewModel.addAccount(
                                Account(
                                    name = name,
                                    balance = balance.toDouble(),
                                    description = description,
                                    icon = icon.toInt(),
                                    iconColor = iconColor.toInt()
                                )
                            ) {
                                navController.navigate(route = "Accounts")
                            }
                        },
                        buttonText = stringResource(id = R.string.create_account_button)
                    )
                }
            }
        }
    }*/
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    upPress: () -> Unit,
    /*    onAccountAdd: () -> Unit*/
) {
    Scaffold(
        topBar = {
            AddAccountTopBar(upPress = upPress, /*onAccountAdd = onAccountAdd()*/)
        }
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.name,
                onNewValue = onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = uiState.description,
                onNewValue = onDescriptionChange
            )
            ValueDropDown(label = stringResource(id = R.string.current_value_label))
/*            BalanceInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = uiState.balance.toString(),
                onNewValue = onBalanceChange
            )*/
            ConfirmButton(
                onClick = {
/*                    viewModel.addAccount(
                        Account(
                            name = name,
                            balance = balance.toDouble(),
                            description = description,
                            icon = icon.toInt(),
                            iconColor = iconColor.toInt()
                        )
                    ) {
                        navController.navigate(route = "Accounts")
                    }*/
                },
                buttonText = stringResource(id = R.string.create_account_button)
            )
        }
    }
}
