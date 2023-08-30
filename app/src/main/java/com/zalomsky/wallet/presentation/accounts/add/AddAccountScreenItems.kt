package com.zalomsky.wallet.presentation.accounts.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.common.icons.downArrow
import com.zalomsky.wallet.presentation.common.icons.downArrowMini
import com.zalomsky.wallet.presentation.common.icons.upArrow
import kotlinx.coroutines.launch


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
            value = value.toString(),   //??????
            onValueChange = { onNewValue.toString() }, //??????
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
fun BalanceControl(
    label: String
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = label + "",
            fontSize = 16.sp,
            fontFamily = splineSansMedium,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            RaiseArrow()
            ReduceArrow()
        }
    }
}

@Composable
fun RaiseArrow() {
    Column {
        Text(text = "Raise")
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
}

@Composable
fun ReduceArrow() {
    Column {
        Text(text = "Reduce")
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

/*
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountTopBar(
    upPress: () -> Unit,
    */
/*    onAccountAdd: (Account) -> Unit*//*

) {
    TopAppBar(
        backgroundColor = Color.White
    ){
        IconButton(
            onClick = upPress,
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "arrow back icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = "Add Account",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(
            onClick = {}*/
/*onAccountAdd()*//*
,
        ) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = "check icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountBottomSheet() {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if(showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Button(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }) {
                Text("Hide bottom sheet")
            }
        }
    }
}