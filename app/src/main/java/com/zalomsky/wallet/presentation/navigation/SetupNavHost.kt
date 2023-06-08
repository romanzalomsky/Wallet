package com.zalomsky.wallet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zalomsky.wallet.WalletRoutes
import com.zalomsky.wallet.presentation.SplashScreen
import com.zalomsky.wallet.presentation.navigation.bottom.NavigationBottom

@Composable
fun SetupNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = WalletRoutes.SPLASH_SCREEN
    ){
        composable(WalletRoutes.SPLASH_SCREEN){
            SplashScreen(navController = navController)
        }
        composable(WalletRoutes.BOTTOM_BAR){
            NavigationBottom()
        }
    }
}
