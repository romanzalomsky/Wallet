package com.zalomsky.wallet

import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.accounts.AccountsScreen
import com.zalomsky.wallet.presentation.accounts.addAccountScreen
import com.zalomsky.wallet.presentation.accounts.editAccountScreen
import com.zalomsky.wallet.presentation.accounts.navigateToAddAccountScreen
import com.zalomsky.wallet.presentation.accounts.navigateToEditAccountScreen
import com.zalomsky.wallet.presentation.categories.CategoriesScreen
import com.zalomsky.wallet.presentation.categories.addCategoryScreen
import com.zalomsky.wallet.presentation.categories.navigateToAddCategoryScreen
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
        bottomRoutes(
            navController = navController
        )
        addAccountScreen(
            onBackPressed = upPress
        )
        editAccountScreen (
            onBackPressed = upPress
        )
        addCategoryScreen (
            onBackPressed = upPress
        )
    }
}

fun NavGraphBuilder.bottomRoutes(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    composable(HomeSections.ACCOUNTS.route) {
        AccountsScreen(
            onRegularAccountAdd = {
                navController.navigateToAddAccountScreen(REGULAR)
            },
            onSavingAccountAdd = {
                navController.navigateToAddAccountScreen(SAVING)
            },
            onDebtAccountAdd = {
                navController.navigateToAddAccountScreen(DEBT)
            },
            onAccountEdit = { accountId ->
                navController.navigateToEditAccountScreen(id = accountId)
            }
        )
    }
    composable(HomeSections.CATEGORIES.route) {
        CategoriesScreen(
            onCategoryAdd = {
                navController.navigateToAddCategoryScreen()
            }
        )
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

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}