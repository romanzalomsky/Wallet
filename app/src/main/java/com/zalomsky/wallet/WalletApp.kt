package com.zalomsky.wallet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.zalomsky.wallet.presentation.navigation.SetupNavHost
import com.zalomsky.wallet.presentation.ui.theme.WalletTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WalletApp(){

    WalletTheme {

        Surface(
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxSize()
        ) {
            SetupNavHost()
        }
    }
}

