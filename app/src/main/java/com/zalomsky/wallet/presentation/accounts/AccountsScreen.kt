package com.zalomsky.wallet.presentation.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zalomsky.wallet.R
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountsScreen(
    navController: NavHostController
){
    val viewModel: AccountsScreenViewModel = hiltViewModel()
    val accounts = viewModel.accounts.observeAsState(listOf()).value

    Scaffold(
        backgroundColor = backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "addScreen") },
                backgroundColor = systemColor
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ){
        Column {
            AccountsHeader(label = stringResource(id = R.string.your_credit_card_header))
            AccountLabel(label = stringResource(id = R.string.your_current_card_label))
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(accounts){account ->
                    AccountCard(
                        name = account.name,
                        balance = account.balance,
                        account = account,
                        description = account.description,
                        icon = account.icon,
                        iconColor = account.iconColor,
                        navController = navController
                    )
                }
            }
            AccountsHeader(label = stringResource(id = R.string.your_accounts_header))
        }
    }
}

@Composable
fun AccountsHeader(
    label: String
) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = label,
        fontFamily = splineSansMedium,
        fontSize = 22.sp,
        modifier = Modifier
            .padding(horizontal = 25.dp)
    )
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun AccountLabel(
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                fontFamily = splineSansMedium,
                fontSize = 18.sp
            )
        }
    }
}
