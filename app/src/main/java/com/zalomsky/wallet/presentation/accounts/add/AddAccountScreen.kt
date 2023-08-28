package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.zalomsky.wallet.R
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.common.icons.arrowBack
import com.zalomsky.wallet.presentation.common.icons.cardIcon
import com.zalomsky.wallet.presentation.common.icons.downArrow
import com.zalomsky.wallet.presentation.common.icons.downArrowMini
import com.zalomsky.wallet.presentation.common.icons.upArrow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    navController: NavHostController
){
    val viewModel : AddAccountScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddAccountView(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange
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
    onDescriptionChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            AddAccountTopBar()
        }
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AddAccountsHeader()
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
            BalanceControl(
                label = stringResource(id = R.string.balance_label)
            )
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

@Composable
fun AddAccountsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(horizontal = 15.dp)
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
                    .padding(horizontal = 15.dp)
                    .size(45.dp)
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                    }
                    .background(systemColor)
                    .clickable {

                    }
            ){
                Icon(
                    painter = painterResource(id = cardIcon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}

@Composable
fun NameInputFields(
    labelText: String,
    labelModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    value: String,
    onNewValue: (String) -> Unit
) {
    Column {
        Text(
            text = labelText,
            fontSize = 16.sp,
            fontFamily = splineSansMedium,
            modifier = labelModifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onNewValue,
            modifier = textFieldModifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(56.dp),
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Black,
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.LightGray
            )
        )
    }
}

@Composable
fun DescriptionInputFields(
    labelText: String,
    labelModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    value: String,
    onNewValue: (String) -> Unit
) {
    Column {
        Text(
            text = labelText,
            fontSize = 16.sp,
            fontFamily = splineSansMedium,
            modifier = labelModifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onNewValue,
            modifier = textFieldModifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(56.dp),
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Black,
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.LightGray
            )
        )
    }
}

@Composable
fun ValueDropDown(
    label: String
) {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    expanded = true
                }
        ){
            Row {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontFamily = splineSansMedium
                )
                Icon(
                    painter = painterResource(id = downArrowMini),
                    contentDescription = "arrow down",
                    modifier = Modifier
                        .size(25.dp)
                )
                DropdownMenu(
                    expanded = expanded ,
                    onDismissRequest = { expanded = false }
                ) {
                    val listOfValues = listOf("RUB", "EURO", "BYN", "DOLLAR")
                    DropdownMenuItem(
                        onClick = {
                            text = listOfValues[1]
                        }
                    ) {
                        Text(listOfValues.get(1))
                    }
                    DropdownMenuItem(
                        onClick = {
                            text = listOfValues[2]
                        }
                    ) {
                        Text(listOfValues.get(2))
                    }
                }
                Text(text = "$text")
            }
        }
    }
}

@Composable
fun BalanceControl(
    label: String
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontFamily = splineSansMedium,
        )
        Row {
            RaiseArrow()
            ReduceArrow()
        }
    }
}

@Composable
fun RaiseArrow() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(45.dp)
            .graphicsLayer {
                clip = true
                shape = CircleShape
            }
            .background(buttonBackColor)
            .clickable {

            }
    ) {
        Icon(
            painter = painterResource(id = upArrow),
            contentDescription = "Raise",
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
        )
    }
}

@Composable
fun ReduceArrow() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(45.dp)
            .graphicsLayer {
                clip = true
                shape = CircleShape
            }
            .background(Color.Red)
            .clickable {

            }
    ) {
        Icon(
            painter = painterResource(id = downArrow),
            contentDescription = "Reduce",
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
        )
    }
}
@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .height(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = systemColor
        )
    ) {
        Text(text = buttonText, color = Color.White, textAlign = TextAlign.Center)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountTopBar(

) {
    Scaffold(
        backgroundColor = systemColor,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = arrowBack),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}