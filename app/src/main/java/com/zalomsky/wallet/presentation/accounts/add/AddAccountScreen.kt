package com.zalomsky.wallet.presentation.accounts.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.zalomsky.wallet.domain.model.AccountType.*
import com.zalomsky.wallet.domain.model.AccountType.Companion.DEBT
import com.zalomsky.wallet.domain.model.AccountType.Companion.REGULAR
import com.zalomsky.wallet.domain.model.AccountType.Companion.SAVING
import com.zalomsky.wallet.presentation.accounts.AccountUiState
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAccountScreen(
    upPress: () -> Unit,
    state: Any?
){
    val addAccountScreenViewModel : AddAccountScreenViewModel = hiltViewModel()
    val uiState by addAccountScreenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(upPress = upPress)
        }
    ) {
        AddView(state = state as String)
    }
}

@Composable
fun AppBar(
    upPress: () -> Unit
) {
    val addAccountScreenViewModel : AddAccountScreenViewModel = hiltViewModel()
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
                addAccountScreenViewModel.addAccount(upPress)
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

@Composable
fun AddView(
    state: String
) {
    val addAccountScreenViewModel : AddAccountScreenViewModel = hiltViewModel()
    val uiState by addAccountScreenViewModel.uiState.collectAsStateWithLifecycle()

    when(state){
        REGULAR -> {
            RegularAccountView(
                uiState = uiState,
                onNameChange = addAccountScreenViewModel::onNameChange,
                onDescriptionChange = addAccountScreenViewModel::onDescriptionChange,
                onBalanceChange = addAccountScreenViewModel::onBalanceChange
            )
        }
        SAVING -> {
            SavingAccountView(
                uiState = uiState,
                onNameChange = addAccountScreenViewModel::onNameChange,
                onDescriptionChange = addAccountScreenViewModel::onDescriptionChange,
                onBalanceChange = addAccountScreenViewModel::onBalanceChange,
                onTargetChange = addAccountScreenViewModel::onTargetChange
            )
        }
        DEBT -> {
            DebtAccountView(
                uiState = uiState,
                onNameChange = addAccountScreenViewModel::onNameChange,
                onDescriptionChange = addAccountScreenViewModel::onDescriptionChange,
                onBalanceChange = addAccountScreenViewModel::onBalanceChange
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegularAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
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
            ConfirmButton(typeOfAccount = REGULAR)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SavingAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit,
    onTargetChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
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
            TargetInputField(
                labelText = stringResource(id = R.string.target_label),
                value = uiState.account.target,
                onNewValue = onTargetChange
            )
            ConfirmButton(typeOfAccount = SAVING)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DebtAccountView(
    uiState: AccountUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBalanceChange: (Double) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp),
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
            ConfirmButton(typeOfAccount = DEBT)
        }
    }
}

@Composable
fun ConfirmButton(
    typeOfAccount: String,
) {
    val context = LocalContext.current
    val addAccountScreenViewModel : AddAccountScreenViewModel = hiltViewModel()
    val uiState by addAccountScreenViewModel.uiState.collectAsStateWithLifecycle()
    Button(
        onClick = {
            uiState.account.type = typeOfAccount
            Toast.makeText(
                context,
                "Account type: ${typeOfAccount}",
                Toast.LENGTH_LONG
            ).show();
        },
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = systemColor,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(8.dp)
    ) {
        AccountTypeField(text = typeOfAccount)
    }
}