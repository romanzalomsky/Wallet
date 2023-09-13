package com.zalomsky.wallet.presentation.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zalomsky.wallet.MainDestinations
import com.zalomsky.wallet.defaultNavOptions
import com.zalomsky.wallet.presentation.categories.add.AddCategory
import com.zalomsky.wallet.presentation.categories.edit.EditCategory

fun NavController.navigateToAddCategoryScreen() =
    navigate("${MainDestinations.ADD_CATEGORY_ROUTE}", defaultNavOptions())

fun NavController.navigateToEditCategoryScreen(id: Long) =
    navigate("${MainDestinations.EDIT_CATEGORY_ROUTE}/${id}", defaultNavOptions())

fun NavGraphBuilder.addCategoryScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.ADD_CATEGORY_ROUTE){
        AddCategory(onBackPressed = onBackPressed)
    }
}

fun NavGraphBuilder.editCategoryScreen(
    onBackPressed: () -> Unit
){
    composable(MainDestinations.EDIT_CATEGORY_ROUTE + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})){
        EditCategory(onBackPressed = onBackPressed, it.arguments?.getString("id"))
    }
}