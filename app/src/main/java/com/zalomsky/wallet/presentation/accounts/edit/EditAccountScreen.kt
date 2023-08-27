package com.zalomsky.wallet.presentation.accounts.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.editBackgroundColor
import com.zalomsky.wallet.presentation.common.color.grey
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.common.icons.cardIcon
import com.zalomsky.wallet.presentation.common.icons.upArrow
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccountScreen(
    navController: NavHostController,
    id: String?
){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    var iconColor by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<EditAccountScreenViewModel>()
    val account = viewModel.account.observeAsState().value

    id?.toLong()?.let {
        viewModel.getAccountById(id = it)
    }

    Scaffold(
        backgroundColor = Color(editBackgroundColor),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "Accounts") },
                backgroundColor = buttonBackColor
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.updateAccounts(
                                Account(
                                    id = account!!.id,
                                    name = name,
                                    balance = balance.toDouble(),
                                    description = description,
                                    icon = icon.toInt(),
                                    iconColor = iconColor.toInt()
                                )
                            ) {
                                navController.navigate(route = "Accounts")
                            }
                        }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Card(
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(id = R.string.account_header),
                        fontFamily = aksharMedium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(56.dp),
                        textStyle = TextStyle(color = Color.Black),
                        label = {
                            Text(
                                text = account?.name.toString().uppercase(),
                                fontSize = 12.sp,
                                fontFamily = aksharMedium
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = {description = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(56.dp),
                        textStyle = TextStyle(color = Color.Black),
                        label = {
                            Text(
                                text = account?.description.toString().uppercase(),
                                fontSize = 12.sp,
                                fontFamily = aksharMedium,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(id = R.string.balance_header),
                        fontFamily = aksharMedium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = balance,
                        onValueChange = { balance = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(56.dp),
                        textStyle = TextStyle(color = Color.Black),
                        label = {
                            Text(
                                text = "balance".uppercase(),
                                fontSize = 12.sp,
                                fontFamily = aksharMedium
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = stringResource(id = R.string.on_value_change_header),
                        fontFamily = aksharMedium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(45.dp)
                            .graphicsLayer {
                                clip = true
                                shape = CircleShape
                            }
                            .background(Color.Blue)
                            .clickable {

                            }
                    ) {
                        Icon(
                            painter = painterResource(id = upArrow),
                            contentDescription = "Up",
                            tint = Color.White,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Row{
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
                        if(icon.isEmpty()){
                            icon = cardIcon.toString()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Row{
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
                        if(iconColor.isEmpty()){
                            iconColor = grey.toString()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "",
                            tint = Color.Red,
                            modifier = Modifier
                        )
                        Text(
                            text = stringResource(id = R.string.delete_account_button),
                            fontSize = 15.sp,
                            fontFamily = aksharMedium,
                            color = Color.Red,
                            modifier = Modifier
                                .clickable{
                                    viewModel.deleteAccounts {
                                        navController.navigate(route = "Accounts")
                                    }
                                }
                        )
                    }
                }
            }
        }
    }

}

