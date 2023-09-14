package com.zalomsky.wallet.domain.validator

import com.zalomsky.wallet.domain.model.Account
import javax.inject.Inject

class AccountValidator @Inject constructor(){

    operator fun invoke(account: Account): Result<Unit> =
        when {
            account.name.isBlank() -> Result.failure(Exception("Name should't be blank!!!"))
            else -> Result.success(Unit)
        }
}