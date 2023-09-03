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

fun NavController.navigateToEditAccountScreen(id: Long) =
    navigate("${MainDestinations.EDIT_ACCOUNT_ROUTE}/$id", defaultNavOptions())

fun NavGraphBuilder.addAccountScreen(
    onBackPressed: () -> Unit
) {
    composable(MainDestinations.ADD_ACCOUNT_ROUTE + "/{state}", arguments = listOf(navArgument("state"){type = NavType.StringType})){
        AddAccountScreen(upPress = onBackPressed, it.arguments?.getString("state"))
    }
}

fun NavGraphBuilder.editAccountScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.EDIT_ACCOUNT_ROUTE + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})){
        EditAccountScreen(upPress = onBackPressed, it.arguments?.getString("id"))
    }
}