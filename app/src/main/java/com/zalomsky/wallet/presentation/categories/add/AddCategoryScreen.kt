package com.zalomsky.wallet.presentation.categories.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.editBackgroundColor
import com.zalomsky.wallet.presentation.common.color.grey
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.common.icons.categoriesIcon
import com.zalomsky.wallet.presentation.listOfCategoryIcons
import com.zalomsky.wallet.presentation.listOfColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddCategoryScreen(
    onCategoryAdded: () -> Unit,
    upPress: () -> Unit
){
    val viewModel = hiltViewModel<AddCategoryScreenViewModel>()

    var name by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var circleColor by remember { mutableStateOf("") }

    Scaffold(
        backgroundColor = Color(editBackgroundColor),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*navController.navigate(route = "Categories")*/ },
                backgroundColor = buttonBackColor
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.addCategory(
                                Category(
                                    name = name,
                                    icon = icon.toInt(),
                                    circleColor = circleColor.toInt(),
                                    amount = amount.toDouble(),
                                )
                            ) {
                                /*navController.navigate(route = "Categories")*/
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
                .padding(top = 10.dp)
        ) {
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "category name".uppercase(),
                        fontFamily = aksharMedium,
                        fontSize = 14.sp,
                        color = Color(grey)
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        textStyle = TextStyle(color = Color.Black),
                        label = {
                            Text(
                                text = "category name".uppercase(),
                                fontSize = 12.sp,
                                fontFamily = aksharMedium
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "amount".uppercase(),
                        fontFamily = aksharMedium,
                        fontSize = 14.sp,
                        color = Color(grey)
                    )
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        textStyle = TextStyle(color = Color.Black),
                        label = {
                            Text(
                                text = "amount".uppercase(),
                                fontSize = 12.sp,
                                fontFamily = aksharMedium
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 3.dp
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "icon".uppercase(),
                        fontFamily = aksharMedium,
                        fontSize = 14.sp,
                        color = Color(grey)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row {
                        LazyRow(
                            userScrollEnabled = true
                        ){
                            items(listOfCategoryIcons){ icons ->
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
                            icon = categoriesIcon.toString()
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "color".uppercase(),
                        fontFamily = aksharMedium,
                        fontSize = 14.sp,
                        color = Color(grey)
                    )
                    Row{
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
                                            circleColor = colors.toString()
                                        }
                                )
                            }
                        }

                        if(circleColor.isEmpty()){
                            circleColor = grey.toString()
                        }
                    }
                }
            }
        }
    }
}