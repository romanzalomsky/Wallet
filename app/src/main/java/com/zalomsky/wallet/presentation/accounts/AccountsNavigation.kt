package com.zalomsky.wallet.presentation.accounts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions
import com.zalomsky.wallet.presentation.accounts.add.AddAccountScreen

fun NavController.navigateToAddAccountScreen() =
    navigate("${MainDestinations.ADD_ACCOUNT_ROUTE}", defaultNavOptions())

fun NavGraphBuilder.addAccountScreen(
    onBackPressed: () -> Unit,
/*    onAccountAdd: () -> Unit*/
) {
    composable(MainDestinations.ADD_ACCOUNT_ROUTE){
        AddAccountScreen(upPress = onBackPressed)
    }
}