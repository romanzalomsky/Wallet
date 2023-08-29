package com.zalomsky.wallet.presentation.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansLight
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountsScreen(
    onAccountAdd: () -> Unit
){
    val viewModel: AccountsScreenViewModel = hiltViewModel()
    val accounts = viewModel.accounts.observeAsState(listOf()).value

    Scaffold(
        topBar = {
            AccountsTopBar(onAccountAdd)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ){
        Column {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(accounts){item ->
                    AccountListItem(account = item, /*onAccountClicked = {}*/)
                }
            }
        }
    }
}

@Composable
fun AccountListItem(
    account: Account,
) {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        elevation = 1.dp,
        border = BorderStroke(1.dp, Color(account.iconColor)),
        modifier = paddingModifier
            .width(355.dp)
            .height(70.dp)
            .clickable { }
        /*navController.navigate(route = "editScreen" + "/${account.id}")*/
    ) {
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .size(45.dp)
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                    }
                    .background(Color(account.iconColor))
            ){
                Icon(
                    painter = painterResource(id = account.icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Column {
                Text(
                    text = account.name,
                    fontFamily = splineSansMedium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = account.balance.toString() + " $",
                    fontFamily = splineSansMedium,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = account.description,
                    fontFamily = splineSansLight,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray,
                    modifier = Modifier.padding(22.dp)
                )
            }
        }
    }
}

@Composable
fun AccountsTopBar(
    onAccountAdd: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ){
        IconButton(
            onClick = {  },
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Menu icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = "Accounts",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = onAccountAdd ) {
            Icon(Icons.Filled.Add, contentDescription = "Add", modifier = Modifier.size(30.dp))
        }
    }
}

