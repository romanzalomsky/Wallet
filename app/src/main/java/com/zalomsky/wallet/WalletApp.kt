package com.zalomsky.wallet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zalomsky.wallet.presentation.BottomNavigationBar
import com.zalomsky.wallet.presentation.common.theme.WalletTheme
import com.zalomsky.wallet.presentation.navigation.WalletNavHost
import com.zalomsky.wallet.presentation.navigation.navigateSingleBottomTo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WalletApp() {

    WalletTheme {

        val navController = rememberNavController()

        Scaffold(
/*            topBar = {
                 TopAppBar(
                     title = {
                         Text(
                            text = "title".uppercase(),
                            color = Color.White,
                            modifier = Modifier
                         )
                     },
                     backgroundColor = systemColor,
                     navigationIcon = {
                         Icon(
                             painter = painterResource(id = hiddenListIcon),
                             contentDescription = "",
                             tint = buttonObjectColor,
                             modifier = Modifier
                                 .size(25.dp)
                                 .clickable {
                                     navController.navigate(route = "Overview")
                                 }
                         )
                     }
                 )
            },*/
            bottomBar = {
                BottomNavigationBar(
                    allScreens = bottomBarScreens,
                    onTabSelected = { bottom ->
                        navController.navigateSingleBottomTo(bottom.route)
                    },
                    navController = navController
                )
            }
        ) { innerPadding ->
            WalletNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}