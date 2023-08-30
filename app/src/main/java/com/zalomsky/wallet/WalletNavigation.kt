package com.zalomsky.wallet

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.zalomsky.wallet.presentation.accounts.AccountsScreen
import com.zalomsky.wallet.presentation.accounts.addAccountScreen
import com.zalomsky.wallet.presentation.accounts.editAccountScreen
import com.zalomsky.wallet.presentation.accounts.navigateToAddAccountScreen
import com.zalomsky.wallet.presentation.accounts.navigateToEditAccountScreen
import com.zalomsky.wallet.presentation.categories.CategoriesScreen
import com.zalomsky.wallet.presentation.overview.OverviewScreen
import com.zalomsky.wallet.presentation.transactions.TransactionsScreen

fun NavGraphBuilder.walletNavGraph(
    navController: NavHostController,
    upPress: () -> Unit
){
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.CATEGORIES.route,
    ){
        bottomRoutes(navController = navController)
        addAccountScreen(
            onBackPressed = upPress
        )
        editAccountScreen {
            /*onBackPressed = upPress*/
        }
    }
}

fun NavGraphBuilder.bottomRoutes(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    composable(HomeSections.ACCOUNTS.route) {
        AccountsScreen(
            onAccountAdd = {
                navController.navigateToAddAccountScreen()
            },
            onAccountEdit = {
                navController.navigateToEditAccountScreen()
            }
        )
    }
    composable(HomeSections.CATEGORIES.route) {
        CategoriesScreen()
    }
    composable(HomeSections.TRANSACTIONS.route) {
        TransactionsScreen()
    }
    composable(HomeSections.OVERVIEW.route) {
        OverviewScreen()
    }
}

fun NavController.navigateToAccountScreen() =
    navigate(MainDestinations.ACCOUNT_ROUTE, defaultNavOptions())

fun defaultNavOptions(popUp: String? = null, inclusive: Boolean = false) = navOptions {
    launchSingleTop = true

    popUp?.let {
        restoreState = true

        popUpTo(popUp) {
            this.inclusive = inclusive

            saveState = true
        }
    }
}
