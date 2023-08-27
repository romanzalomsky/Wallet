package com.zalomsky.wallet.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zalomsky.wallet.BottomDestinations
import com.zalomsky.wallet.presentation.common.color.buttonBackColor
import com.zalomsky.wallet.presentation.common.color.buttonObjectColor
import com.zalomsky.wallet.presentation.common.fonts.rubikMedium
import com.zalomsky.wallet.presentation.common.icons.hiddenListIcon

@Composable
fun BottomNavigationBar(
    allScreens: List<BottomDestinations>,
    onTabSelected: (BottomDestinations) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
){
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 20.dp
    ) {
        allScreens.forEach { screen ->

            val selected = screen.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                onClick = { onTabSelected(screen) },
                selectedContentColor = Color(0xFF0B9AFA),
                unselectedContentColor = Color.Gray,
                label = {
                    Text(
                        text = screen.route,
                        fontSize = 10.sp,
                        fontFamily = rubikMedium
                    )
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BadgedBox(
                            badge = { },
                            modifier = Modifier
                        ) {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.route,
                                modifier = Modifier
                                    .size(23.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopNavigationBar(
    navController: NavController,
){
    Scaffold(
        backgroundColor = buttonBackColor,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row{
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 10.dp)
            ){
                Icon(
                    painter = painterResource(id = hiddenListIcon),
                    contentDescription = "",
                    tint = buttonObjectColor,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.navigate(route = "overview")
                        }
                )
            }
        }
    }
}

@Composable
fun CustomIcon(
    yourIcon: String,
    insertIcon: Int,
){
    Icon(
        painter = painterResource(id = insertIcon),
        contentDescription = "",
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(40.dp)
            .clickable {
                yourIcon
            }
    )
}

@Composable
fun ScreenHeader(
    title: String
){
    Box(
     modifier = Modifier
         .height(120.dp)
         .background(
             color = Color.White
         )
    ){
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = title,
                    color = Color.Black,
                    fontFamily = rubikMedium,
                    fontSize = 23.sp
                )
            }
        }
    }
}

@Composable
fun Line(){
    Canvas(
        modifier = Modifier.fillMaxWidth()){
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Gray,
            strokeWidth = 1F
        )
    }
}

