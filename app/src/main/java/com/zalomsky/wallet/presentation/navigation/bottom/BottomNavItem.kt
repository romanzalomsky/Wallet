package com.zalomsky.wallet.presentation.navigation.bottom

data class BottomNavItem(

    val name: String,
    val route: String,
    val icon: Int,
    val badgeCount: Int = 0
)
