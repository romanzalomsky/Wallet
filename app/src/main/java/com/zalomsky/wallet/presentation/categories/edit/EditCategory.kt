package com.zalomsky.wallet.presentation.categories.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.presentation.ScreenTopBar
import com.zalomsky.wallet.presentation.accounts.ButtonDelete
import com.zalomsky.wallet.presentation.categories.CategoryDetailViewModel
import com.zalomsky.wallet.presentation.categories.CategoryUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditCategory(
    onBackPressed: () -> Unit,
    id: String?
){
    val viewModel: CategoryDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    id?.toLong()?.let { viewModel.getCategoryById(id = it) }

    Scaffold(
        topBar = {
            ScreenTopBar(
                appHeader = "Edit Category",
                onDone = { viewModel.updateCategory(onBackPressed) },
                onBackPressed = onBackPressed
            )
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        EditView(
            uiState = uiState,
            onNameChange = viewModel::onNameChange,
            onAmountChange = viewModel::onAmountChange,
            onCategoryDelete = {viewModel.deleteCategory(onBackPressed)}
        )
    }
}

@Composable
fun EditView(
    uiState: CategoryUiState,
    onNameChange: (String) -> Unit,
    onAmountChange: (Double) -> Unit,
    onCategoryDelete: () -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
/*        NameInputFields(
            labelText = stringResource(id = R.string.name_label),
            value = uiState.category.name,
            onNewValue = onNameChange
        )
        BalanceInputFields(
            labelText = stringResource(id = R.string.amount_label),
            value = uiState.category.amount,
            onValueChange = onAmountChange
        )*/
        ButtonDelete(onDelete = onCategoryDelete)
    }
}