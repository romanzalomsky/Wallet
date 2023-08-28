package com.zalomsky.wallet

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.zalomsky.wallet.presentation.common.components.WalletBottomBar
import com.zalomsky.wallet.presentation.common.theme.WalletTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WalletApp() {

    WalletTheme {

        val appState = rememberWalletAppState()

        Scaffold(
            bottomBar = {
                if(appState.shouldShowBottomBar){
                    WalletBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
            scaffoldState = appState.scaffoldState
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = MainDestinations.HOME_ROUTE
            ){
                walletNavGraph()
            }
        }
    }
}