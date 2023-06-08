package com.zalomsky.wallet.presentation.navigation.bottom

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zalomsky.wallet.R
import com.zalomsky.wallet.WalletRoutes
import com.zalomsky.wallet.presentation.common.theme.WalletTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavigationBottom(){

    WalletTheme {

        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Accounts",
                            route = WalletRoutes.ACCOUNTS_SCREEN,
                            icon = R.drawable.creditcard
                        ),
                        BottomNavItem(
                            name = "Categories",
                            route = WalletRoutes.CATEGORIES_SCREEN,
                            icon = R.drawable.piechart
                        ),
                        BottomNavItem(
                            name = "Transactions",
                            route = WalletRoutes.TRANSACTIONS_SCREEN,
                            icon = R.drawable.list
                        ),
                        BottomNavItem(
                            name = "Overview",
                            route = WalletRoutes.OVERVIEW_SCREEN,
                            icon = R.drawable.barchart
                        )
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        ) {
            NavBottom(navController = navController)
        }

    }
}

@Composable
fun BottomNavigationBar(

    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){

    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color(0xFF03A9F4),
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0){
                            BadgedBox(
                                badge = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    modifier = Modifier
                                        .size(27.dp),
                                    contentDescription = item.name
                                )
                            }
                        } else{
                            Icon(
                                painter = painterResource(id = item.icon),
                                modifier = Modifier
                                    .size(27.dp),
                                contentDescription = item.name
                            )
                        }
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )

                        //For animation
/*                        if (selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp
                            )
                        }*/
                    }
                }
            )
        }
    }
}