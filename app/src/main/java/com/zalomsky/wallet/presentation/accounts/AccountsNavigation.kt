package com.zalomsky.wallet.presentation.accounts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions
import com.zalomsky.wallet.presentation.accounts.add.AddAccountScreen
import com.zalomsky.wallet.presentation.accounts.edit.EditAccountScreen

fun NavController.navigateToAddAccountScreen(state: String) =
    navigate("${MainDestinations.ADD_ACCOUNT_ROUTE}/$state", defaultNavOptions())

fun NavController.navigateToEditAccountScreen(id: Long, state: String) =
    navigate("${MainDestinations.EDIT_ACCOUNT_ROUTE}/$id/$state", defaultNavOptions())

fun NavGraphBuilder.addAccountScreen(
    onBackPressed: () -> Unit
) {
    composable(MainDestinations.ADD_ACCOUNT_ROUTE + "/{state}", arguments = listOf(navArgument("state"){type = NavType.StringType})){
        val stateArgument = it.arguments?.getString("state").orEmpty()
        AddAccountScreen(onBackPressed = onBackPressed, stateArgument)
    }
}

fun NavGraphBuilder.editAccountScreen(
    onBackPressed: () -> Unit
){
    composable(
        route = MainDestinations.EDIT_ACCOUNT_ROUTE + "/{id}" + "/{state}",
        arguments = listOf(
            navArgument("id"){type = NavType.StringType},
            navArgument("state"){type = NavType.StringType}
        )
    ){
        EditAccountScreen(
            onBackPressed = onBackPressed,
            it.arguments?.getString("state"),
            it.arguments?.getString("id"),
        )
    }
}