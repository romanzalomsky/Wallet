package com.zalomsky.wallet.presentation.accounts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.common.fonts.splineSansBold
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@Composable
fun AccountCard(
    name: String,
    balance: Double,
    description: String,
    icon: Int,
    iconColor: Int,
    account: Account,
    navController: NavController,
){
    Card(
        backgroundColor = Color(iconColor),
        modifier = Modifier
            .height(125.dp)
            .width(300.dp)
            .padding(horizontal = 25.dp)
            .clickable {
                navController.navigate(route = "editScreen" + "/${account.id}")
            },
        elevation = 0.dp,
        shape = RoundedCornerShape(17.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier.padding(top = 8.dp)
            ){
                Text(
                    text = description,
                    fontFamily = splineSansMedium,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 11.dp)
        ) {
            Text(
                text = name,
                fontFamily = splineSansMedium,
                color = Color.White,
                fontSize = 17.sp
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(vertical = 20.dp)
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.balance_label),
                    fontSize = 11.sp,
                    color = Color.White,
                    fontFamily = splineSansMedium
                )
                Text(
                    text = "$ " + balance.toString() + " ",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = splineSansMedium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.dots_beauty),
                    fontFamily = splineSansBold,
                    color = Color.White
                )

            }
        }
    }
}