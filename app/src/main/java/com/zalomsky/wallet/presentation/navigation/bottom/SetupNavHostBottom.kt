package com.zalomsky.wallet.presentation.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zalomsky.wallet.WalletRoutes
import com.zalomsky.wallet.presentation.accounts.AccountsScreen
import com.zalomsky.wallet.presentation.categories.CategoriesScreen
import com.zalomsky.wallet.presentation.overview.OverviewScreen
import com.zalomsky.wallet.presentation.transactions.TransactionsScreen

@Composable
fun NavBottom(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = WalletRoutes.CATEGORIES_SCREEN
    ){
        composable(WalletRoutes.ACCOUNTS_SCREEN){
            AccountsScreen()
        }
        composable(WalletRoutes.CATEGORIES_SCREEN){
            CategoriesScreen()
        }
        composable(WalletRoutes.TRANSACTIONS_SCREEN){
            TransactionsScreen()
        }
        composable(WalletRoutes.OVERVIEW_SCREEN){
            OverviewScreen()
        }
    }
}