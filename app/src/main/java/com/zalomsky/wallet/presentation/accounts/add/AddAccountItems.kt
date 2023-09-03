package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.common.icons.downArrowMini


@Composable
fun AccountTypeField(
    text: String
) {
    Column {
        Text(
            text = "Account type: " + text,
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = splineSansMedium,
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun TargetInputField(
    labelText: String,
    labelModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    value: Double,
    onNewValue: (Double) -> Unit
) {
    var text by remember { mutableStateOf(value.toString()) }
    Column {
        Text(
            text = labelText,
            fontSize = 16.sp,
            color = systemTextColor,
            fontFamily = splineSansMedium,
            modifier = labelModifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                val newValue = newText.toDoubleOrNull() ?: 0.0
                onNewValue(newValue)
            },
            modifier = textFieldModifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(56.dp),
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Black,
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = systemColor
            )
        )
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
            color = systemTextColor,
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
                focusedBorderColor = systemColor
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
    var text by remember { mutableStateOf(value) }
    Column {
        Text(
            text = labelText,
            fontSize = 16.sp,
            color = systemTextColor,
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
                focusedBorderColor = systemColor
            )
        )
    }
}

@Composable
fun BalanceInputFields(
    labelText: String,
    labelModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    value: Double,
    onNewValue: (Double) -> Unit
) {
    var text by remember { mutableStateOf(value.toString()) }
    Column {
        Text(
            text = labelText,
            fontSize = 16.sp,
            color = systemTextColor,
            fontFamily = splineSansMedium,
            modifier = labelModifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                val newValue = newText.toDoubleOrNull() ?: 0.0
                onNewValue(newValue)
            },
            modifier = textFieldModifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(56.dp),
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Black,
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = systemColor
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
                    color = systemTextColor,
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



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegularAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.account.name,
                onNewValue = onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = uiState.account.description,
                onNewValue = onDescriptionChange
            )
            ValueDropDown(label = stringResource(id = R.string.current_value_label))
            BalanceInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = uiState.account.balance,
                onNewValue = onBalanceChange
            )
            ConfirmButton(
                typeOfAccount = AccountType.REGULAR,
                uiState = uiState
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SavingAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.account.name,
                onNewValue = onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = uiState.account.description,
                onNewValue = onDescriptionChange
            )
            ValueDropDown(label = stringResource(id = R.string.current_value_label))
            BalanceInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = uiState.account.balance,
                onNewValue = onBalanceChange
            )
            TargetInputField(
                labelText = stringResource(id = R.string.target_label),
                value = uiState.account.target,
                onNewValue = onTargetChange
            )
            ConfirmButton(
                typeOfAccount = AccountType.SAVING,
                uiState = uiState
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DebtAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.account.name,
                onNewValue = onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = uiState.account.description,
                onNewValue = onDescriptionChange
            )
            ValueDropDown(label = stringResource(id = R.string.current_value_label))
            BalanceInputFields(
                labelText = stringResource(id = R.string.balance_label),
                value = uiState.account.balance,
                onNewValue = onBalanceChange
            )
            ConfirmButton(
                typeOfAccount = AccountType.DEBT,
                uiState = uiState
            )
        }
    }
}

@Composable
fun ConfirmButton(
    typeOfAccount: String,
    uiState: AccountUiState
) {
    val context = LocalContext.current
    Button(
        onClick = {
            uiState.account.type = typeOfAccount
            Toast.makeText(
                context,
                "Account type: ${typeOfAccount}",
                Toast.LENGTH_LONG
            ).show();
        },
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = systemColor,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(8.dp)
    ) {
        AccountTypeField(text = typeOfAccount)
    }
}