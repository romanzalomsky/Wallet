package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountValidationUseCase: AccountValidationUseCase
) {
    suspend operator fun invoke(account: Account): Result<Unit> =
        withContext(Dispatchers.IO){
            accountValidationUseCase.invoke(account).onFailure{
                return@withContext Result.failure(it)
            }

            val result = runCatching {
                accountRepository.insertAccount(account = account)
            }

            result
        }
}