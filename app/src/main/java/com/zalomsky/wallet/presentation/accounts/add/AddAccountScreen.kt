package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.grey
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.common.icons.cardIcon
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    navController: NavHostController,
    onNameChange: (String) -> Unit,
){
    val viewModel : AddAccountScreenViewModel = hiltViewModel()

    var name by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    var iconColor by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    val openDialog = remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        backgroundColor = backgroundColor,
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
                        }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .padding(top = 5.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.add_card_header),
                        fontFamily = splineSansMedium,
                        fontSize = 22.sp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 25.dp)
                            .size(45.dp)
                            .graphicsLayer {
                                clip = true
                                shape = CircleShape
                            }
                            .background(systemColor)
                            .clickable {
                                openDialog.value = true
                            }
                    ){
                        Icon(
                            painter = painterResource(id = cardIcon),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .size(25.dp)
                        )
                        if (openDialog.value) {
                            AlertDialog(
                                onDismissRequest = {
                                    openDialog.value = false
                                },
                                title = { Text(text = "Подтверждение действия") },
                                text = { Text("Вы действительно хотите удалить выбранный элемент?") },
                                buttons = {
                                    Button(
                                        onClick = { openDialog.value = false }
                                    ) {
                                        Text("OK", fontSize = 22.sp)
                                    }
                                },
                                modifier = Modifier
                                    .height(400.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.name_label),
                fontSize = 16.sp,
                fontFamily = splineSansMedium,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(56.dp),
                textStyle = TextStyle(color = Color.Black)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.description_label),
                fontSize = 16.sp,
                fontFamily = splineSansMedium,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(56.dp),
                textStyle = TextStyle(color = Color.Black)
            )
            Spacer(modifier = Modifier.height(10.dp))
            DropDownMenuExample()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.balance_label),
                fontSize = 16.sp,
                fontFamily = splineSansMedium,
                modifier = Modifier
                    .padding(horizontal = 25.dp)
            )
            OutlinedTextField(
                value = balance,
                onValueChange = { balance = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(56.dp),
                textStyle = TextStyle(color = Color.Black)
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
        }
    }
}

@Composable
fun DropDownMenuExample() {
    val items = listOf("RUB", "BYN", "$", "EUR")
    val selectedItem = remember { mutableStateOf(items[0]) }

    Column(
        modifier = Modifier
            .padding(horizontal = 25.dp)
    ) {

        val expanded = remember { mutableStateOf(false) }
        val onDropdownClick: () -> Unit = { expanded.value = true }
        Box(
            modifier = Modifier
                .clickable(onClick = onDropdownClick)
                .border(1.dp, Color.Black)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .width(110.dp)
        ) {
            Text(text = selectedItem.value)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        // Выпадающий список
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    selectedItem.value = item
                    expanded.value = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

@Composable
fun NameInputFields(
    labelText: String,
    value:String,
    onNewValue: (String) -> Unit
) {
    Text(
        text = labelText,
        fontSize = 16.sp,
        fontFamily = splineSansMedium,
        modifier = Modifier
            .padding(horizontal = 25.dp)
    )
    OutlinedTextField(
        value = value,
        onValueChange = onNewValue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .height(56.dp),
        textStyle = TextStyle(color = Color.Black)
    )
}
