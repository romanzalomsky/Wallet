package com.zalomsky.wallet.features.accounts.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.domain.model.AccountEntity
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.features.common.color.backgroundColor
import com.zalomsky.wallet.features.common.color.systemTextColor
import com.zalomsky.wallet.features.common.components.AccountAppBar
import com.zalomsky.wallet.features.common.components.AccountElement
import com.zalomsky.wallet.features.common.components.TypeAlertDialog
import com.zalomsky.wallet.features.common.components.WalletIconButton
import com.zalomsky.wallet.features.common.components.fontSize
import com.zalomsky.wallet.features.common.components.horizontalPaddingSize
import com.zalomsky.wallet.features.common.components.paddingBetweenElements
import com.zalomsky.wallet.features.common.components.topAppBarFontSize
import com.zalomsky.wallet.features.common.fonts.splineSansLight
import com.zalomsky.wallet.features.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountsScreen(
    onRegularAccountAdd: () -> Unit,
    onSavingAccountAdd: () -> Unit,
    onDebtAccountAdd: () -> Unit,
    onAccountEdit: (Long, String) -> Unit
) {
    val viewModel: AccountsScreenViewModel = hiltViewModel()
    val accounts by viewModel.accounts.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AccountAppBar(onRegularAccountAdd, onSavingAccountAdd, onDebtAccountAdd)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(accounts) { item ->
                AccountElement(accountEntity = item, onAccountEdit = onAccountEdit)
            }
        }
    }
}

