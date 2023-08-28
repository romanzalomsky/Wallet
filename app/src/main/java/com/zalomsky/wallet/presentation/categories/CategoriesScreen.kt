package com.zalomsky.wallet.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesScreen(){

    val viewModel = hiltViewModel<CategoriesScreenViewModel>()
    val categories = viewModel.categories.observeAsState(listOf()).value

    Scaffold(
        backgroundColor = backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*navController.navigate(route = "addCategoryScreen")*/ },
                backgroundColor = systemColor
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
        Column {
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                LazyRow(
                    userScrollEnabled = true
                ){
                    items(categories){ category ->
                        CategoryCard(
                            name = category.name,
                            icon = category.icon,
                            amount = category.amount,
                            category = category,
                            circleColor = category.circleColor,
/*                            navController = navController*/
                        )
                    }
                }
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

@Composable
fun CategoryCard(
    name: String,
    icon: Int,
    amount: Double,
    category: Category,
    circleColor: Int,
/*    navController: NavController*/
){
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(80.dp),
        elevation = 0.dp,
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontFamily = aksharMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(45.dp)
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                    }
                    .background(Color(circleColor))
                    .clickable {
                        /*navController.navigate(route = "editCategoryScreen" + "/${category.id}")*/
                    }
            ){
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = amount.toString(),
                fontFamily = aksharMedium
            )
        }
    }
}
