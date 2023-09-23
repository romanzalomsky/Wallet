package com.zalomsky.wallet.presentation.categories.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.presentation.accounts.edit.ButtonDelete
import com.zalomsky.wallet.presentation.common.color.backgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditCategoryScreen(
    onBackPressed: () -> Unit,
    id: String?
){

    val viewModel: EditCategoryScreenViewModel = hiltViewModel()
    val category = viewModel.category.observeAsState().value

    id?.toLong()?.let { viewModel.getCategoryById(id = it) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(15.dp)
            .background(backgroundColor)
            .fillMaxSize(),
    ) {
        ButtonDelete(
            onDeleteAccount = { viewModel.deleteCategory(onBackPressed) }
        )
    }
}