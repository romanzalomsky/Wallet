package com.zalomsky.wallet.features.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.R
import com.zalomsky.wallet.features.common.fonts.aksharMedium

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

@Composable
fun WalletIconButton(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.Gray,
            modifier = Modifier.size(25.dp)
        )
    }
}