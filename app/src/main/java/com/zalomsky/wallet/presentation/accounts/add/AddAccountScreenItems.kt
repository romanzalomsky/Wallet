package com.zalomsky.wallet.presentation.accounts.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
