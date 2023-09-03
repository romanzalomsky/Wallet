package com.zalomsky.wallet.presentation.accounts

import com.zalomsky.wallet.domain.model.Account

data class AccountUiState(
    val account: Account = Account.defaultInstance()
)
