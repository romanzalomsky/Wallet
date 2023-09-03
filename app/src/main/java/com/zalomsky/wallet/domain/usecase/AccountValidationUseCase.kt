package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.model.Account
import javax.inject.Inject

class AccountValidationUseCase @Inject constructor(){

    operator fun invoke(account: Account): Result<Unit> =
        when {
            account.name.isBlank() -> Result.failure(Exception("Name should't be blank!!!"))
            else -> Result.success(Unit)
        }
}