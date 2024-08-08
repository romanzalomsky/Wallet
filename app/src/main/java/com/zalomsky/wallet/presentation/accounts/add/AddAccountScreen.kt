package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.WalletAlertDialog
import com.zalomsky.wallet.presentation.WalletIconBox
import com.zalomsky.wallet.presentation.WalletIconButton
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.purple
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.components.WalletAppBar
import com.zalomsky.wallet.presentation.common.components.WalletDoubleInputField
import com.zalomsky.wallet.presentation.common.components.WalletStringInputField
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    onBackPressed: () -> Unit,
    state: String?
) {
    val viewModel: AddAccountScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            WalletAppBar(
                text = stringResource(id = R.string.add_account_header),
                upPress = onBackPressed,
                onClick = { viewModel.addAccount(onBackPressed) }
            )
        }
    ) {
        when (state) {
            REGULAR -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
            SAVING -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
            DEBT -> {
                AddAccountView(
                    state = state,
                    uiState = uiState,
                    onNameChange = viewModel::onNameChange,
                    onDescriptionChange = viewModel::onDescriptionChange,
                    onBalanceChange = viewModel::onBalanceChange,
                    onTargetChange = viewModel::onTargetChange
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountView(
    state: String?,
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit
) {
    val showAlertDialog = remember { mutableStateOf(false) }
    var iconSet by remember { mutableStateOf(R.drawable.creditcard1) }
    var colorSet by remember { mutableStateOf(purple) }

    Scaffold(
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp)
        ) {
            WalletStringInputField(
                labelText = stringResource(id = R.string.name_label),
                value = uiState.accountEntity.name,
                onNewValue = onNameChange
            )
            WalletStringInputField(
                labelText = stringResource(id = R.string.description_label),
                value = uiState.accountEntity.description,
                onNewValue = onDescriptionChange
            )
            WalletDoubleInputField(
                value = uiState.accountEntity.balance,
                onNewValue = onBalanceChange
            )
            if (state == SAVING) {
                WalletDoubleInputField(
                    value = uiState.accountEntity.target,
                    onNewValue = onTargetChange
                )
            }
            WalletIconBox(
                icon = iconSet,
                color = Color(colorSet),
                onClick = {showAlertDialog.value = true}
            )
            if(showAlertDialog.value){
                AlertDialog(
                    iconSet = iconSet,
                    showAlertDialog = showAlertDialog,
                )
            }
            uiState.accountEntity.type = state.toString()
        }
    }
}

@Composable
fun AlertDialog(
    iconSet: Int,
    showAlertDialog: MutableState<Boolean>
) {
    WalletAlertDialog(
        onDismissRequest = { showAlertDialog.value = false },
        icon = iconSet,
        content = {
            WalletHorizontalPager()
        },
        buttons = {
            Button(
                onClick = { showAlertDialog.value = false }
            ) {
                Text(text = "OK")
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WalletHorizontalPager() {
    HorizontalPager(2) { page ->
        when(page) {
            1 -> {
                Box(
                    modifier = Modifier.width(265.dp).height(300.dp)
                ){
                    LazyVerticalGrid(columns = GridCells.Fixed(5)){
                        /*items(listOfColors){ color ->
                            ColorPage(
                                iconColor = color,
                                onColorAdd = {
                                    uiState.accountEntity.iconColor = color
                                    colorSet = color
                                })
                        }*/
                    }
                }
            }
            2 -> {
                Box(
                    modifier = Modifier.width(265.dp).height(300.dp)
                ){
                    LazyVerticalGrid(columns = GridCells.Fixed(5)){
                       /* items(listOfAccountsIcons){ account ->
                            IconPage(
                                icon = account,
                                onIconAdd = {
                                    uiState.accountEntity.icon = account
                                    iconSet = account
                                })
                        }*/
                    }
                }
            }
        }
    }
}