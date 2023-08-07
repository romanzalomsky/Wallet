package com.zalomsky.wallet.presentation.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.AppState
import com.zalomsky.wallet.presentation.Line
import com.zalomsky.wallet.presentation.ScreenHeader
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.moneyTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansLight
import com.zalomsky.wallet.presentation.common.fonts.splineSansRegular

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountsScreen(
    navController: NavController
){
    val viewModel = hiltViewModel<AccountsScreenViewModel>()
    val accounts = viewModel.accounts.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "addScreen") },
                backgroundColor = buttonBackColor
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
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            ScreenHeader(title = AppState.accountsScreenTitle)
            LazyColumn(
                userScrollEnabled = true
            ) {
                items(accounts){ account ->
                    AccountCard(
                        name = account.name,
                        balance = account.balance,
                        account = account,
                        icon = account.icon,
                        iconColor = account.iconColor,
                        navController = navController
                    )
                }
            }
            if(accounts.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "empty list".uppercase(),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun AccountCard(
    name: String,
    balance: Double,
    icon: Int,
    iconColor: Int,
    account: Account,
    navController: NavController,
){
    Card(
        modifier = Modifier
            .height(60.dp)
            .clickable {
                navController.navigate(route = "editScreen" + "/${account.id}")
            },
        elevation = 0.dp,
    ) {
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = Color(iconColor),
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    Text(
                        text = balance.toString() + " ",
                        fontSize = 20.sp,
                        color = moneyTextColor,
                        fontFamily = splineSansLight
                    )
                    Text(
                        text = name,
                        fontFamily = splineSansRegular
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Line()
                }
            }
        }
    }
}
