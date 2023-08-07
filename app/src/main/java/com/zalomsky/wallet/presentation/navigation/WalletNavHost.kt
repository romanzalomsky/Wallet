package com.zalomsky.wallet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zalomsky.wallet.Accounts
import com.zalomsky.wallet.Add
import com.zalomsky.wallet.AddCategory
import com.zalomsky.wallet.Categories
import com.zalomsky.wallet.Edit
import com.zalomsky.wallet.EditCategory
import com.zalomsky.wallet.HiddenList
import com.zalomsky.wallet.Overview
import com.zalomsky.wallet.Transactions
import com.zalomsky.wallet.presentation.accounts.AccountsScreen
import com.zalomsky.wallet.presentation.accounts.add.AddAccountScreen
import com.zalomsky.wallet.presentation.accounts.edit.EditAccountScreen
import com.zalomsky.wallet.presentation.categories.CategoriesScreen
import com.zalomsky.wallet.presentation.categories.add.AddCategoryScreen
import com.zalomsky.wallet.presentation.categories.edit.EditCategoryScreen
import com.zalomsky.wallet.presentation.overview.OverviewScreen
import com.zalomsky.wallet.presentation.transactions.TransactionsScreen

@Composable
fun WalletNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Categories.route,
        modifier = modifier
    ) {
/*        composable(route = "splash"){
            SplashScreen(navController = navController)
        }*/
        composable(route = Accounts.route) {
            AccountsScreen(
                navController = navController
            )
        }
        composable(route = Add.route) {
            AddAccountScreen(navController = navController)
        }
        composable(route = Edit.route + "/{id}", arguments = listOf(navArgument("id") {type = NavType.StringType})){
            EditAccountScreen(navController = navController, it.arguments?.getString("id"))
        }
        composable(route = Categories.route) {
            CategoriesScreen(
                navController = navController
            )
        }
        composable(route = AddCategory.route){
            AddCategoryScreen(navController = navController)
        }
        composable(route = EditCategory.route + "/{id}", arguments = listOf(navArgument("id") {type = NavType.StringType})){
            EditCategoryScreen(navController = navController, it.arguments?.getString("id") )
        }
        composable(route = Overview.route) {
            OverviewScreen()
        }
        composable(route = HiddenList.route){
            OverviewScreen()
        }
        composable(route = Transactions.route) {
            TransactionsScreen(
                onAddCategory = {
                }
            )
        }
    }
}

fun NavHostController.navigateSingleBottomTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleBottomTo.graph.findStartDestination().id
        ) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
