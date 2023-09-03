package com.zalomsky.wallet.presentation.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions
import com.zalomsky.wallet.presentation.categories.add.AddCategoryScreen

fun NavController.navigateToAddCategoryScreen() =
    navigate("${MainDestinations.ADD_CATEGORY_ROUTE}", defaultNavOptions())

fun NavGraphBuilder.addCategoryScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.ADD_CATEGORY_ROUTE){
        AddCategoryScreen(onCategoryAdded = onBackPressed, upPress = onBackPressed)
    }
}