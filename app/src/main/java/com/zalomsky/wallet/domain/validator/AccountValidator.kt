package com.zalomsky.wallet.domain.validator

import com.zalomsky.wallet.domain.model.AccountEntity
import javax.inject.Inject

class AccountValidator @Inject constructor(){

    operator fun invoke(accountEntity: AccountEntity): Result<Unit> =
        when {
            accountEntity.name.isBlank() -> Result.failure(Exception("Name should't be blank!!!"))
            else -> Result.success(Unit)
        }
}