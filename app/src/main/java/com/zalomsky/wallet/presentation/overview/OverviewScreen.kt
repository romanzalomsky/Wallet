package com.zalomsky.wallet.presentation.overview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OverviewScreen(
    onCalendarShow: () -> Unit = {}
){
    Scaffold(
        topBar = {
            OverviewTopBar(onCalendarShow)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
}

@Composable
fun OverviewTopBar(
    onCalendarShow: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ){
        IconButton(
            onClick = {  },
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Menu icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = "Overview",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor,
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = onCalendarShow) {
            Icon(Icons.Filled.DateRange, contentDescription = "Calendar Show", modifier = Modifier.size(30.dp))
        }
    }
}