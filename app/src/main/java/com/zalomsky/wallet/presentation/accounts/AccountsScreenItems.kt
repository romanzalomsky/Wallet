package com.zalomsky.wallet.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.blackColor
import com.zalomsky.wallet.presentation.common.color.grayColor
import com.zalomsky.wallet.presentation.common.color.greenColor
import com.zalomsky.wallet.presentation.common.color.redColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.color.yellowColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@Composable
fun AccountBottomSheetContent(
    account: Account,
    onAccountEdit: (Long, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(account.iconColor))
        ) {
            Text(text = account.name)
        }
        CircleContent(account = account, onAccountEdit = onAccountEdit)
    }
}

@Composable
fun CircleContent(
    account: Account,
    onAccountEdit: (Long, String) -> Unit
) {
    Spacer(modifier = Modifier.height(30.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        CircleElement(
            background = yellowColor,
            onClick = { onAccountEdit(account.id, account.type) },
            icon = Icons.Outlined.Create,
            text = stringResource(id = R.string.change_circle)
        )
        Spacer(modifier = Modifier.width(85.dp))
        CircleElement(
            background = grayColor,
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.Refresh,
            text = stringResource(id = R.string.balance_circle)
        )
        Spacer(modifier = Modifier.width(85.dp))
        CircleElement(
            background = greenColor,
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.List,
            text = stringResource(id = R.string.list_circle)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(20.dp)
    ) {
        CircleElement(
            background = greenColor,
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.KeyboardArrowUp,
            text = stringResource(id = R.string.top_up_circle)
        )
        Spacer(modifier = Modifier.width(85.dp))
        CircleElement(
            background = redColor,
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.KeyboardArrowDown,
            text = stringResource(id = R.string.debit_circle)
        )
        Spacer(modifier = Modifier.width(85.dp))
        CircleElement(
            background = grayColor,
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.ArrowForward,
            text = stringResource(id = R.string.transfer_circle)
        )
    }
}

@Composable
fun CircleElement(
    background: Color,
    onClick: () -> Unit,
    icon: ImageVector,
    text: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(background)
                .clickable(onClick = onClick)
        ){
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = blackColor,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = splineSansMedium,
            color = systemTextColor,
        )
    }
}