package com.zalomsky.wallet.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.AppState
import com.zalomsky.wallet.presentation.ScreenHeader
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesScreen(
    navController: NavController
){

    val viewModel = hiltViewModel<CategoriesScreenViewModel>()
    val categories = viewModel.categories.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "addCategoryScreen") },
                backgroundColor = buttonBackColor
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(40.dp)
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
            ScreenHeader(title = AppState.categoriesScreenTitle)
            LazyColumn(
                userScrollEnabled = true
            ){
                items(categories){ category ->
                    CategoryCard(
                        name = category.name,
                        icon = category.icon,
                        amount = category.amount,
                        category = category,
                        navController = navController
                    )
                }
            }
            if(categories.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "empty list".uppercase(),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    name: String,
    icon: Int,
    amount: Double,
    category: Category,
    navController: NavController
){
    Card(
        modifier = Modifier
            .height(60.dp)
            .clickable {
                navController.navigate(route = "editCategoryScreen" + "/${category.id}")
            },
        elevation = 0.dp,
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Column {
            Text(text = name)
            Icon(painter = painterResource(id = icon), contentDescription = "")
            Text(text = amount.toString())
        }
    }
}
