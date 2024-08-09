package com.zalomsky.wallet.features.common.components

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountEntity
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.features.common.color.systemTextColor
import com.zalomsky.wallet.features.common.fonts.splineSansLight
import com.zalomsky.wallet.features.common.fonts.splineSansMedium

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountElement(
    accountEntity: AccountEntity,
    onAccountEdit: (Long, String) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingBetweenElements)
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable(
                onClick = { onAccountEdit(accountEntity.id, accountEntity.type) },
                onLongClick = { showBottomSheet = true }
            )
    ) {
        Row {
            IconBox(accountEntity = accountEntity)
            Column {
                Text(
                    text = accountEntity.name,
                    fontFamily = splineSansMedium,
                    fontSize = fontSize,
                    color = systemTextColor,
                    modifier = Modifier.padding(top = paddingBetweenElements)
                )
                when (accountEntity.type) {
                    AccountType.REGULAR.name -> {
                        Text(
                            text = accountEntity.balance.toString() + "$",
                            color = systemTextColor,
                            fontFamily = splineSansMedium,
                        )
                    }

                    AccountType.SAVING.name -> {
                        Text(
                            text = accountEntity.balance.toString() + "$" + " out of " + "${accountEntity.target}" + "$",
                            color = systemTextColor,
                            fontFamily = splineSansMedium,
                        )
                    }

                    AccountType.DEBT.name -> {
                        Text(
                            text = accountEntity.balance.toString() + "$",
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
                    text = accountEntity.description,
                    fontFamily = splineSansLight,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray,
                    modifier = Modifier.padding(22.dp)
                )
            }
        }
    }
}

@Composable
fun IconBox(
    accountEntity: AccountEntity,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(10.dp)
            .size(45.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(accountEntity.iconColor))
    ) {
        Icon(
            painter = painterResource(id = accountEntity.icon),
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
            onDismissRequest = { onDismiss() },
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

