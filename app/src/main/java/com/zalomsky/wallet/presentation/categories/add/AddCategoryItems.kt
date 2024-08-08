package com.zalomsky.wallet.presentation.categories.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium
import com.zalomsky.wallet.presentation.listOfCategoryIcons

@Composable
fun IconAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onIconAdd: () -> Unit
) {
    val openDialog = remember { mutableStateOf(showDialog) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Choose Icon") },
            buttons = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconAlertItem(
                        onIconAdd = onIconAdd
                    )
                }
            }
        )
    }
}

@Composable
fun IconAlertItem(
    onIconAdd: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 25.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)){
            items(listOfCategoryIcons){ category ->
                IconPage(icon = category, onIconAdd = onIconAdd)
            }
        }
    }
}

@Composable
fun IconPage(
    icon: Int,
    onIconAdd: () -> Unit
) {
    IconButton(
        onClick = onIconAdd,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun IconChoice(
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Choose icon",
            fontFamily = splineSansMedium,
            fontSize = 12.sp,
            color = systemTextColor
        )
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "icon choice",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
