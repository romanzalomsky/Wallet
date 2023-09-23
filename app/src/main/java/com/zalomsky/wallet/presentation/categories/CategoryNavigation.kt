package com.zalomsky.wallet.presentation.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions
import com.zalomsky.wallet.presentation.categories.add.AddCategoryScreen
import com.zalomsky.wallet.presentation.categories.edit.EditCategoryScreen

fun NavController.navigateToAddCategoryScreen() =
    navigate("${MainDestinations.ADD_CATEGORY_ROUTE}", defaultNavOptions())

fun NavController.navigateToEditCategoryScreen(id: Long) =
    navigate("${MainDestinations.EDIT_CATEGORY_ROUTE}/$id", defaultNavOptions())

fun NavGraphBuilder.addCategoryScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.ADD_CATEGORY_ROUTE){
        AddCategoryScreen(onCategoryAdded = onBackPressed, upPress = onBackPressed)
    }
}

fun NavGraphBuilder.editCategoryScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.EDIT_CATEGORY_ROUTE + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})){
        EditCategoryScreen(onBackPressed = onBackPressed, id = it.arguments?.getString("id"))
    }
}