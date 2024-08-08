package com.zalomsky.wallet.presentation.accounts.update

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.presentation.WalletAlertDialog
import com.zalomsky.wallet.presentation.WalletIconBox
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.categories.add.ColorPage
import com.zalomsky.wallet.presentation.categories.add.IconPage
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.components.WalletAppBar
import com.zalomsky.wallet.presentation.common.components.WalletDoubleInputField
import com.zalomsky.wallet.presentation.common.components.WalletStringInputField
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun EditAccountScreen(
    onBackPressed: () -> Unit,
    state: String?,
    id: Long
) {
    val viewModel: EditAccountScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val account = viewModel.uiState.value.accountEntity

    LaunchedEffect(id) {
        viewModel.onEvent(AccountEvent.Load(id))
    }

    Scaffold(
        topBar = {
            WalletAppBar(
                text = stringResource(id = R.string.edit_account_header),
                onClick = { viewModel.onEvent(AccountEvent.Update(onBackPressed)) },
                upPress = onBackPressed
            )
        },
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        UpdateAccountView(
            state = state,
            uiState = uiState,
            onNameChange = viewModel::onNameChange,
            onDescriptionChange = viewModel::onDescriptionChange,
            onBalanceChange = viewModel::onBalanceChange,
            onTargetChange = viewModel::onTargetChange,
            onDeleteAccount = { viewModel.deleteAccounts(account, onBackPressed) }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UpdateAccountView(
    state: String?,
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit,
    onDeleteAccount: () -> Unit
) {
    val showAlertDialog = remember { mutableStateOf(false) }
    var iconSet by remember { mutableStateOf(uiState.accountEntity.icon) }
    var colorSet by remember { mutableStateOf(uiState.accountEntity.iconColor) }

    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
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
        if (state == AccountType.SAVING) {
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
            WalletAlertDialog(
                onDismissRequest = { showAlertDialog.value = false },
                icon = iconSet,
                content = {
                    HorizontalPager(2) { page ->
                        if(page == 1){
                            Box(
                                modifier = Modifier.width(265.dp).height(300.dp)
                            ){
                                LazyVerticalGrid(columns = GridCells.Fixed(5)){
                                    items(listOfAccountsIcons){ account ->
                                        IconPage(
                                            icon = account,
                                            onIconAdd = {
                                                uiState.accountEntity.icon = account
                                                iconSet = account
                                            })
                                    }
                                }
                            }
                        }
                        else{
                            Box(
                                modifier = Modifier.width(265.dp).height(300.dp)
                            ){
                                LazyVerticalGrid(columns = GridCells.Fixed(5)){
                                    items(listOfColors){ color ->
                                        ColorPage(
                                            iconColor = color,
                                            onColorAdd = {
                                                uiState.accountEntity.iconColor = color
                                                colorSet = color
                                            })
                                    }
                                }
                            }
                        }
                    }
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
        ButtonDelete(
            onDeleteAccount = onDeleteAccount
        )
    }
}

@Composable
fun ButtonDelete(
    onDeleteAccount: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Red,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(56.dp)
            .clickable(onClick = onDeleteAccount)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "",
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.delete_account_button),
                fontSize = 15.sp,
                fontFamily = aksharMedium,
                color = Color.White
            )
        }
    }
}