package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.AppState.Companion.addAccountScreenTitle
import com.zalomsky.wallet.presentation.ScreenHeader
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.grey
import com.zalomsky.wallet.presentation.common.icons.cardIcon
import com.zalomsky.wallet.presentation.listOfColors
import com.zalomsky.wallet.presentation.listOfIcons

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    navController: NavController
){

    val viewModel = hiltViewModel<AddAccountScreenViewModel>()

    var balance by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    var iconColor by remember { mutableStateOf("") }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "accounts") },
                backgroundColor = buttonBackColor
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.addAccount(
                                Account(
                                    name = name,
                                    balance = balance.toDouble(),
                                    icon = icon.toInt(),
                                    iconColor = iconColor.toInt()
                                )
                            ) {
                                navController.navigate(route = "accounts")
                            }
                        }
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
            ScreenHeader(title = addAccountScreenTitle)
            Spacer(modifier = Modifier.height(15.dp))
            Column(modifier = Modifier.fillMaxHeight()) {

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    textStyle = TextStyle(color = Color.Black),
                    label = {
                        Text(
                            text = "enter account name".uppercase(),
                            fontSize = 12.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                TextField(
                    value = balance,
                    onValueChange = { balance = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    textStyle = TextStyle(color = Color.Black),
                    label = {
                        Text(
                            text = "enter your balance".uppercase(),
                            fontSize = 12.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = "your icon: $icon".uppercase(),
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    LazyRow(
                        userScrollEnabled = true
                    ){
                        items(listOfIcons){ icons ->
                            Icon(
                                painter = painterResource(id = icons),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 3.dp)
                                    .size(40.dp)
                                    .clickable {
                                        icon = icons.toString()
                                    }
                            )
                        }
                    }
                    if(icon.isEmpty()){
                        icon = cardIcon.toString()
                    }
                }
                Text(
                    text = "your icon color: $iconColor".uppercase(),
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    LazyRow(
                        userScrollEnabled = true
                    ){
                        items(listOfColors){ colors ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 3.dp)
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
                    
                    if(iconColor.isEmpty()){
                        iconColor = grey.toString()
                    }
                }
                Text(
                    text = "choose your value".uppercase(),
                    fontSize = 12.sp
                )
            }
        }
    }
}
