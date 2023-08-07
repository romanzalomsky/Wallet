package com.zalomsky.wallet.presentation.categories.edit

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun EditCategoryScreen(
    navController: NavController,
    id: String?
){

    val viewModel = hiltViewModel<EditCategoryScreenViewModel>()
    id?.toLong()?.let { viewModel.getCategoryById(id = it) }

    Icon(
        imageVector = Icons.Rounded.Delete,
        contentDescription = "",
        modifier = Modifier
            .clickable{
                viewModel.deleteCategory {
                    navController.navigate(route = "categories")
                }
            }
    )
}