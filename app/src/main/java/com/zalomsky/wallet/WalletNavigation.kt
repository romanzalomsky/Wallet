package com.zalomsky.wallet

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.zalomsky.wallet.presentation.accounts.AccountsScreen
import com.zalomsky.wallet.presentation.categories.CategoriesScreen
import com.zalomsky.wallet.presentation.overview.OverviewScreen
import com.zalomsky.wallet.presentation.transactions.TransactionsScreen

fun NavGraphBuilder.walletNavGraph(){
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.CATEGORIES.route,
    ){
        addHomeGraph()
    }
}

fun NavGraphBuilder.addHomeGraph(
    modifier: Modifier = Modifier
){
    composable(HomeSections.ACCOUNTS.route) {
        AccountsScreen()
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
