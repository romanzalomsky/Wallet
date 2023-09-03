package com.zalomsky.wallet.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@Composable
fun ScreenTopBar(
    appHeader: String,
    onDone: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ){
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "arrow back icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = appHeader,
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(
            onClick = onDone,
        ) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = "check icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
