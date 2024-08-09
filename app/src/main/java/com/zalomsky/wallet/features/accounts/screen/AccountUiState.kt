package com.zalomsky.wallet.features.accounts.screen

import com.zalomsky.wallet.domain.model.AccountEntity

data class AccountUiState(
    val accountEntity: AccountEntity = AccountEntity.defaultInstance()
)

data class AccountDetails(

    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val balance: String = "",
    val target: String = "",
    val type: String = "",
    val icon: Int = 0,
    val iconColor: Int = 0
)

fun AccountDetails.toAccount(): AccountEntity = AccountEntity(

    id = id,
    name = name,
    description = description,
    balance = balance.toDoubleOrNull() ?: 0.0,
    target = target.toDoubleOrNull() ?: 0.0,
    type = type,
    icon = icon,
    iconColor = iconColor
)

fun AccountEntity.toAccountDetails(): AccountDetails = AccountDetails(

    id = id,
    name = name,
    description = description,
    balance = balance.toString(),
    target = target.toString(),
    type = type,
    icon = icon,
    iconColor = iconColor
)