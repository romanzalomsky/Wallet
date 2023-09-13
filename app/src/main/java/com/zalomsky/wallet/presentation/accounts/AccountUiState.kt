package com.zalomsky.wallet.presentation.accounts

import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.model.AccountType
import com.zalomsky.wallet.presentation.listOfAccountsIcons
import com.zalomsky.wallet.presentation.listOfColors

/*data class AccountUiState(val account: Account = Account.defaultInstance())*/

data class AccountListUiState(val accountList: List<Account> = listOf())

data class AccountDetails(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val balance: String = "",
    val target: String = "",
    val icon: Int = listOfAccountsIcons.random(),
    var type: String = AccountType.REGULAR,
    val iconColor: Int = listOfColors.random()
)

data class AccountUiState(
    val accountDetails: AccountDetails = AccountDetails()
)

fun AccountDetails.toAccount(): Account = Account(
    id = id,
    name = name,
    description = description,
    balance = balance.toDoubleOrNull() ?: 0.0,
    target = target.toDoubleOrNull() ?: 0.0,
    icon = icon,
    type = type,
    iconColor = iconColor
)