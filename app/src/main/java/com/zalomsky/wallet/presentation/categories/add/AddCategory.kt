package com.zalomsky.wallet.presentation.categories.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.presentation.ScreenTopBar
import com.zalomsky.wallet.presentation.categories.CategoryDetailViewModel
import com.zalomsky.wallet.presentation.categories.CategoryUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.editBackgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddCategory(
    onBackPressed: () -> Unit
){
    val viewModel: CategoryDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ScreenTopBar(
                appHeader = "Add Category",
                onDone = {viewModel.addCategory(onBackPressed)},
                onBackPressed = onBackPressed)
        },
        backgroundColor = Color(editBackgroundColor),
        modifier = Modifier.fillMaxSize()
    ) {
        AddView(
            uiState = uiState,
            onNameChange = viewModel::onNameChange,
            onAmountChange = viewModel::onAmountChange
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddView(
    uiState: CategoryUiState,
    onNameChange: (String) -> Unit,
    onAmountChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
        ) {/*
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.category.name,
                onNewValue = onNameChange
            )
            BalanceInputFields(
                labelText = stringResource(id = R.string.amount_label),
                value = uiState.category.amount,
                onValueChange = onAmountChange
            )*/
        }
    }
}