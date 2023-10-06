package com.zalomsky.wallet.presentation.accounts

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.presentation.WalletIconButton
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansLight
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountsScreen(
    onRegularAccountAdd: () -> Unit,
    onSavingAccountAdd: () -> Unit,
    onDebtAccountAdd: () -> Unit,
    onAccountEdit: (Long, String) -> Unit
) {
    val viewModel: AccountsScreenViewModel = hiltViewModel()
    val accounts by viewModel.accounts.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AccountAppBar(onRegularAccountAdd, onSavingAccountAdd, onDebtAccountAdd)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(accounts) { item ->
                    AccountListItem(account = item, onAccountEdit = onAccountEdit)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountAppBar(
    onRegularAccountAdd: () -> Unit,
    onSavingAccountAdd: () -> Unit,
    onDebtAccountAdd: () -> Unit,
) {
    val showDialog = remember { mutableStateOf(false) }

    TopAppBar(
        backgroundColor = Color.White
    ) {
        WalletIconButton(
            icon = Icons.Outlined.Menu,
            description = "Menu icon",
            onClick = {}
        )
        Text(
            text = "Accounts",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor,
        )
        Spacer(Modifier.weight(1f, true))
        WalletIconButton(
            icon = Icons.Filled.Add,
            description = "Add",
            onClick = { showDialog.value = true }
        )
        if (showDialog.value) {
            TypeAlertDialog(
                showDialog = showDialog.value,
                onDismiss = { showDialog.value = false },
                onRegularAccountAdd = onRegularAccountAdd,
                onSavingAccountAdd = onSavingAccountAdd,
                onDebtAccountAdd = onDebtAccountAdd
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AccountListItem(
    account: Account,
    onAccountEdit: (Long, String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if(showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            content = {
                AccountBottomSheetContent(account = account, onAccountEdit = onAccountEdit)
            }
        )
    }
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(3.dp)
            .width(355.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable (
                onClick = { onAccountEdit(account.id, account.type) },
                onLongClick = {showBottomSheet = true}
            )
    ) {
        AccountListBody(account = account)
    }
}

@Composable
fun AccountListBody(
    account: Account
) {
    Row {
        IconBox(account = account)
        Column {
            Text(
                text = account.name,
                fontFamily = splineSansMedium,
                fontSize = 15.sp,
                color = systemTextColor,
                modifier = Modifier.padding(top = 10.dp)
            )
            when (account.type) {
                AccountType.REGULAR -> {
                    Text(
                        text = account.balance.toString() + "$",
                        color = systemTextColor,
                        fontFamily = splineSansMedium,
                    )
                }
                AccountType.SAVING -> {
                    Text(
                        text = account.balance.toString() + "$" + " out of " + "${account.target}" + "$",
                        color = systemTextColor,
                        fontFamily = splineSansMedium,
                    )
                }
                AccountType.DEBT -> {
                    Text(
                        text = account.balance.toString() + "$",
                        color = systemTextColor,
                        fontFamily = splineSansMedium,
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = account.description,
                fontFamily = splineSansLight,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(22.dp)
            )
        }
    }
}

@Composable
fun IconBox(
    account: Account,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(10.dp)
            .size(45.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(account.iconColor))
    ) {
        Icon(
            painter = painterResource(id = account.icon),
            contentDescription = " ",
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
    }
}

@Composable
fun AlertItem(
    alertText: String,
    icon: Int,
    text: String,
    onAccountAdd: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White)
                .padding(horizontal = 25.dp)
                .clickable(onClick = onAccountAdd)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column{
                    Text(
                        text = alertText,
                        fontSize = 20.sp,
                        color = systemTextColor
                    )
                    Text(
                        text = text,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TypeAlertDialog(
    onRegularAccountAdd: () -> Unit,
    onSavingAccountAdd: () -> Unit,
    onDebtAccountAdd: () -> Unit,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    val openDialog = remember { mutableStateOf(showDialog) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = { Text(text = "New Account") },
            buttons = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    AlertItem(
                        alertText = stringResource(id = R.string.regular_item),
                        icon = R.drawable.regular_acc,
                        text = "Cash, card, ...",
                        onAccountAdd = onRegularAccountAdd
                    )
                    AlertItem(
                        alertText = stringResource(id = R.string.saving_item),
                        icon = R.drawable.saving_acc,
                        text = "Saving, goal, ...",
                        onAccountAdd = onSavingAccountAdd
                    )
                    AlertItem(
                        alertText = stringResource(id = R.string.debt_item),
                        icon = R.drawable.debt_acc,
                        text = "Credit, mortgage, ...",
                        onAccountAdd = onDebtAccountAdd
                    )
                }
            }
        )
    }
}
