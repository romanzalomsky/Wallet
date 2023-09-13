package com.zalomsky.wallet.presentation.accounts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.R
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@Composable
fun RegularAccountView(
    accountDetails: AccountDetails,
    onNewValue: (AccountDetails) -> Unit = {}
) {
    Column {
        StandartFields(accountDetails, onNewValue)
    }
}

@Composable
fun SavingAccountView(
    accountDetails: AccountDetails,
    onNewValue: (AccountDetails) -> Unit = {}
) {
    Column {
        StandartFields(accountDetails, onNewValue)
        Label(modifier = Modifier.padding(top = 10.dp), labelText = stringResource(id = R.string.target_label))
        OutlinedTextField(
            value = accountDetails.target,
            onValueChange = { onNewValue(accountDetails.copy(target = it)) },
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
fun StandartFields(
    accountDetails: AccountDetails,
    onNewValue: (AccountDetails) -> Unit = {}
) {
    Label(modifier = Modifier.padding(top = 0.dp), labelText = stringResource(id = R.string.name_label))
    OutlinedTextField(
        value = accountDetails.name,
        onValueChange = {onNewValue(accountDetails.copy(name = it))},
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
    Label(modifier = Modifier.padding(top = 10.dp), labelText = stringResource(id = R.string.description_label))
    OutlinedTextField(
        value = accountDetails.description,
        onValueChange = { onNewValue(accountDetails.copy(description = it)) },
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
    Label(modifier = Modifier.padding(top = 10.dp), labelText = stringResource(id = R.string.balance_label))
    OutlinedTextField(
        value = accountDetails.balance,
        onValueChange = { onNewValue(accountDetails.copy(balance = it)) },
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

@Composable
fun Label(
    labelText: String,
    modifier: Modifier
) {
    Text(
        text = labelText,
        fontSize = 16.sp,
        color = systemTextColor,
        fontFamily = splineSansMedium,
        modifier = modifier
            .padding(horizontal = 15.dp)
    )
}

@Composable
fun ButtonDelete(
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Red,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(56.dp)
            .clickable(onClick = onDelete)
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
            )
            Text(
                text = "DELETE  ",
                fontSize = 15.sp,
                fontFamily = aksharMedium,
                color = Color.White
            )
        }
    }
}
