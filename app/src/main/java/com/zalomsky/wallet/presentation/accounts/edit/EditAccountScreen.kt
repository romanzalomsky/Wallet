package com.zalomsky.wallet.presentation.accounts.edit

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.R
import com.zalomsky.wallet.presentation.accounts.add.BalanceInputFields
import com.zalomsky.wallet.presentation.accounts.add.DescriptionInputFields
import com.zalomsky.wallet.presentation.accounts.add.NameInputFields
import com.zalomsky.wallet.presentation.accounts.add.ValueDropDown
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.aksharMedium
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccountScreen(
    upPress: () -> Unit,
    id: String?,
){
    val editAccountViewModel = hiltViewModel<EditAccountScreenViewModel>()
    val account = editAccountViewModel.account.observeAsState().value

    id?.toLong()?.let {
        editAccountViewModel.getAccountById(id = it)
    }

    Scaffold(
        topBar = {
            EditAppBar (upPress = upPress)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            NameInputFields(
                labelText = stringResource(id = R.string.name_label),
                value = account?.name.toString(),
                onNewValue = editAccountViewModel::onNameChange
            )
            DescriptionInputFields(
                labelText = stringResource(id = R.string.description_label),
                value = account?.description.toString(),
                onNewValue = editAccountViewModel::onDescriptionChange
            )
            ValueDropDown(label = stringResource(id = R.string.current_value_label))
            account?.balance?.let { it1 ->
                BalanceInputFields(
                    labelText = stringResource(id = R.string.balance_label),
                    value = it1,
                    onNewValue = editAccountViewModel::onBalanceChange
                )
            }
            ButtonDelete(upPress = upPress)
        }
    }
}

@Composable
fun EditAppBar(
    upPress: () -> Unit
) {
    val editAccountViewModel = hiltViewModel<EditAccountScreenViewModel>()
    TopAppBar(
        backgroundColor = Color.White
    ) {
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
            text = "Edit Account",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor
        )
        Spacer(Modifier.weight(1f, true))
        val context = LocalContext.current
        IconButton(
            onClick = {
                editAccountViewModel.updateAccount(upPress)
                Toast.makeText(
                    context,
                    "Account Edited Successfully",
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
fun ButtonDelete(
    upPress: () -> Unit
) {
    val editAccountViewModel = hiltViewModel<EditAccountScreenViewModel>()
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Red,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(56.dp),
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
            )
            Text(
                text = stringResource(id = R.string.delete_account_button),
                fontSize = 15.sp,
                fontFamily = aksharMedium,
                color = Color.White,
                modifier = Modifier
                    .clickable{
                        editAccountViewModel.deleteAccounts(upPress)
                    }
            )
        }
    }
}