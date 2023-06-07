package com.zalomsky.wallet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zalomsky.wallet.WalletRoutes
import com.zalomsky.wallet.presentation.SplashScreen
import com.zalomsky.wallet.presentation.StartScreen

@Composable
fun SetupNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WalletRoutes.SPLASH_SCREEN
    ){
        composable(WalletRoutes.SPLASH_SCREEN){
            SplashScreen(navController = navController)
        }
        composable(WalletRoutes.START_SCREEN){
           StartScreen()
        }
    }
}