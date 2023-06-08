package com.zalomsky.wallet

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.zalomsky.wallet.presentation.navigation.SetupNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WalletApp() {

    val navController = rememberNavController()
    SetupNavHost(navController = navController)
}