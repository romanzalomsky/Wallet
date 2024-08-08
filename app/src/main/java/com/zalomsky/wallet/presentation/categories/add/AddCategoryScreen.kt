package com.zalomsky.wallet.presentation.categories.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.presentation.WalletIconButton
import com.zalomsky.wallet.presentation.accounts.add.BalanceInputFields
import com.zalomsky.wallet.presentation.accounts.add.StringInputField
import com.zalomsky.wallet.presentation.categories.CategoryUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.common.icons.familyCategoryIcon

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddCategoryScreen(
    onCategoryAdded: () -> Unit,
    upPress: () -> Unit
){
    val viewModel: AddCategoryScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val category by viewModel.category.observeAsState()

    val specialIcon = uiState.category

    Scaffold(
        floatingActionButton = {
           FloatingActionButton(
               onClick = { viewModel.addCategory(onCategoryAdded) },
               backgroundColor = systemColor,
               modifier = Modifier
                   .padding(10.dp)
           ) {
               Icon(
                   imageVector = Icons.Outlined.Check,
                   contentDescription = "category icon",
                   tint = Color.White,
                   modifier = Modifier
                       .size(45.dp)
               )
           }
        },
        topBar = {
            AddCategoryAppBar(
                text = "Add Category",
                upPress = upPress,
                addIcon = {  }
            )
        }
    ){
        AddCategoryView(
            uiState = uiState,
            onNameChange = viewModel::onNameChange,
            onBalanceChange = viewModel::onAmountChange,
            icon = familyCategoryIcon
        )
    }
}

@Composable
fun AddCategoryAppBar(
    text: String,
    upPress: () -> Unit,
    addIcon: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ) {
        WalletIconButton(
            icon = Icons.Outlined.ArrowBack,
            description = "arrow back icon",
            onClick = upPress
        )
        Text(
            text = text,
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        WalletIconButton(
            icon = Icons.Outlined.MoreVert,
            description = "check icon",
            onClick = addIcon
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddCategoryView(
    uiState: CategoryUiState,
    onNameChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    icon: Int
) {
    val showDialog = remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(15.dp)
            .background(backgroundColor)
            .fillMaxSize(),
    ) {
        StringInputField(
            labelText = stringResource(id = R.string.name_label),
            value = uiState.category.name,
            onNewValue = onNameChange
        )
        BalanceInputFields(
            labelText = stringResource(id = R.string.amount_label),
            value = uiState.category.amount,
            onNewValue = onBalanceChange
        )
        IconChoice(onClick = { showDialog.value = true })
        if(showDialog.value){
            IconAlertDialog(
                showDialog = showDialog.value,
                onDismiss = { showDialog.value = false },
                onIconAdd = { uiState.category.icon = icon } // TODO: Обработать нажатие
            )
        }
    }
}
