package com.zalomsky.wallet.features.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.wallet.HomeSections
import com.zalomsky.wallet.features.common.color.systemTextColor
import com.zalomsky.wallet.features.common.fonts.rubikMedium
import com.zalomsky.wallet.features.common.fonts.splineSansMedium

@Composable
fun WalletAppBar(
    text: String,
    upPress: () -> Unit,
    onClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ) {
        WalletIconButton(
            icon = Icons.Outlined.ArrowBack,
            description = "arrow back icon",
            onClick = upPress
        )
        Text(
            text = text,
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        WalletIconButton(
            icon = Icons.Outlined.Check,
            description = "check icon",
            onClick = onClick
        )
    }
}

@Composable
fun WalletBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {

    val currentSectionPosition = tabs.indexOfFirst { it.route == currentRoute }
    val currentSection = tabs[currentSectionPosition]

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        tabs.forEach { section ->
            val selected = section == currentSection

            val text = stringResource(section.title)

            BottomNavigationItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BadgedBox(
                            badge = { },
                            modifier = Modifier
                        ) {
                            Icon(
                                painter = painterResource(id = section.icon),
                                contentDescription = text,
                                modifier = Modifier
                                    .size(23.dp)
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = text,
                        fontSize = 10.sp,
                        fontFamily = rubikMedium
                    )
                },
                selected = selected,
                selectedContentColor = Color(0xFF0B9AFA),
                unselectedContentColor = Color.Gray,
                onClick = { navigateToRoute(section.route) }
            )
        }
    }
}