package com.zalomsky.wallet.presentation.drawer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions

fun NavController.navigateMenuToDrawer() =
    navigate("${MainDestinations.DRAWER_ROUTE}", defaultNavOptions())

fun NavGraphBuilder.menuDrawerScreen(){
    composable(MainDestinations.DRAWER_ROUTE){
        MenuDrawerScreen()
    }
}