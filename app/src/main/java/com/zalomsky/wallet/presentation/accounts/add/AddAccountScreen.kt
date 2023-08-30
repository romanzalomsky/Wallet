package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.wallet.R
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    onAccountAdded: () -> Unit,
    upPress: () -> Unit
){
    val addAccountScreenViewModel : AddAccountScreenViewModel = hiltViewModel()
    val uiState by addAccountScreenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White
            ){
                IconButton(
                    onClick = upPress,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "arrow back icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(25.dp)
                    )
                }
                Text(
                    text = "Add Account",
                    fontFamily = splineSansMedium,
                    fontSize = 20.sp,
                    color = systemTextColor
                )
                Spacer(Modifier.weight(1f, true))
                val context = LocalContext.current
                IconButton(
                    onClick = {
                        addAccountScreenViewModel.addAccount(onAccountAdded)
                        Toast.makeText(
                            context,
                            "Account Added Successfully",
                            Toast.LENGTH_LONG
                        ).show();
                    },
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
    ) {
        AddAccountView(
            uiState = uiState,
            onNameChange = addAccountScreenViewModel::onNameChange,
            onDescriptionChange = addAccountScreenViewModel::onDescriptionChange,
            onBalanceChange = addAccountScreenViewModel::onBalanceChange
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        NameInputFields(
            labelText = stringResource(id = R.string.name_label),
            value = uiState.account.name,
            onNewValue = onNameChange
        )
        DescriptionInputFields(
            labelText = stringResource(id = R.string.description_label),
            value = uiState.account.description,
            onNewValue = onDescriptionChange
        )
        ValueDropDown(label = stringResource(id = R.string.current_value_label))
        BalanceInputFields(
            labelText = stringResource(id = R.string.balance_label),
            value = uiState.account.balance,
            onNewValue = onBalanceChange
        )
/*            ConfirmButton(
            onClick = onAccountAdded,
            buttonText = stringResource(id = R.string.create_account_button)
        )*/
    }
}
