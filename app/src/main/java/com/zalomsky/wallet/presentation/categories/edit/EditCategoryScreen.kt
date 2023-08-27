package com.zalomsky.wallet.presentation.categories.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.editBackgroundColor
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.listOfAccountsIcons

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditCategoryScreen(
    navController: NavController,
    id: String?
){

    val viewModel = hiltViewModel<EditCategoryScreenViewModel>()
    val category = viewModel.category.observeAsState().value

    var name by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    var circleColor by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    id?.toLong()?.let { viewModel.getCategoryById(id = it) }

    Scaffold(
        backgroundColor = Color(editBackgroundColor),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "Categories") },
                backgroundColor = buttonBackColor
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.updateCategory(
                                Category(
                                    id = category!!.id,
                                    name = name,
                                    icon = icon.toInt(),
                                    circleColor = circleColor.toInt(),
                                    amount = amount.toDouble()
                                )
                            ) {
                                navController.navigate(route = "Categories")
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
                    .height(100.dp)
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
                        text = "name".uppercase(),
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
                                text = category?.name.toString().uppercase(),
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
                    .height(170.dp)
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
                            text = "Delete category".uppercase(),
                            fontSize = 15.sp,
                            fontFamily = aksharMedium,
                            color = Color.Red,
                            modifier = Modifier
                                .clickable{
                                    viewModel.deleteCategory {
                                        navController.navigate(route = "Categories")
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}