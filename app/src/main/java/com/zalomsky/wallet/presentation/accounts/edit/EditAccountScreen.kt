package com.zalomsky.wallet.presentation.accounts.edit

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun EditAccountScreen(
    navController: NavController,
    id: String?
){

    val viewModel = hiltViewModel<EditAccountScreenViewModel>()
    val account = viewModel.account.observeAsState().value
    id?.toLong()?.let { viewModel.getAccountById(id = it) }

    Icon(
        imageVector = Icons.Rounded.Delete,
        contentDescription = "",
        modifier = Modifier
            .clickable{
                viewModel.deleteAccounts {
                    navController.navigate(route = "accounts")
                }
            }
    )
}