package com.zalomsky.wallet.presentation.accounts.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@Composable
fun StringInputField(
    labelText: String,
    value: String,
    onNewValue: (String) -> Unit
) {
    Column {
        Text(
            text = labelText,
            fontSize = 16.sp,
            color = systemTextColor,
            fontFamily = splineSansMedium,
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onNewValue,
            modifier = Modifier
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
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                val newValue = newText.toDoubleOrNull() ?: 0.0
                onNewValue(newValue)
            },
            modifier = Modifier
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
fun TargetInputField(
    labelText: String,
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
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                val newValue = newText.toDoubleOrNull() ?: 0.0
                onNewValue(newValue)
            },
            modifier = Modifier
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