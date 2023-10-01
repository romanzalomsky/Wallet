package com.zalomsky.wallet.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.zalomsky.wallet.presentation.common.color.systemTextColor


@Composable
fun WalletIconBox(
    icon: Int,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(10.dp)
            .size(45.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .clickable (onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = " ",
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
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

@Composable
fun WalletAlertDialog(
    onDismissRequest: () -> Unit,
    buttons: @Composable () -> Unit,
    content: @Composable () -> Unit,
    icon: Int,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        AlertDialogContent(
            buttons = buttons,
            shape = shape,
            icon = icon,
            content = content,
            backgroundColor = backgroundColor,
        )
    }
}

@Composable
internal fun AlertDialogContent(
    buttons: @Composable () -> Unit,
    content: @Composable () -> Unit,
    icon: Int,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    WalletSurface(
        shape = shape,
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ){
            AlertDialogHeader(icon = icon)
            content()
            buttons()
        }
    }
}

@Composable
fun AlertDialogHeader(
    icon: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = "Choose your icon",
            fontSize = 17.sp,
            color = systemTextColor
        )
    }
}

@Composable
fun AlertNavButtons(

) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(145.dp)
        ) {
            Text(text = "Icon")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(145.dp)
        ) {
            Text(text = "Color")
        }
    }
}


