package com.zalomsky.wallet.presentation.accounts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions
import com.zalomsky.wallet.presentation.accounts.add.AddAccountScreen
import com.zalomsky.wallet.presentation.accounts.edit.EditAccountScreen

fun NavController.navigateToAddAccountScreen() =
    navigate("${MainDestinations.ADD_ACCOUNT_ROUTE}", defaultNavOptions())

fun NavController.navigateToEditAccountScreen() =
    navigate("${MainDestinations.EDIT_ACCOUNT_ROUTE}", defaultNavOptions())

fun NavGraphBuilder.addAccountScreen(
    onBackPressed: () -> Unit
) {
    composable(MainDestinations.ADD_ACCOUNT_ROUTE){
        AddAccountScreen(onAccountAdded = onBackPressed, upPress = onBackPressed)
    }
}

fun NavGraphBuilder.editAccountScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.EDIT_ACCOUNT_ROUTE){
        EditAccountScreen(onAccountEdited = onBackPressed, id = "${id}")
    }
}