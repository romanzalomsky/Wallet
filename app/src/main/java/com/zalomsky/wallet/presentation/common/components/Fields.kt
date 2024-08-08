package com.zalomsky.wallet.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
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
fun WalletStringInputField(
    labelText: String,
    value: String,
    onNewValue: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onNewValue,
        placeholder = {
            Text(
                text = labelText,
                fontSize = 14.sp,
                color = systemTextColor,
                fontFamily = splineSansMedium
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(60.dp),
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

@Composable
fun WalletDoubleInputField(
    value: Double,
    onNewValue: (Double) -> Unit
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = { onNewValue(it.toDouble()) },
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