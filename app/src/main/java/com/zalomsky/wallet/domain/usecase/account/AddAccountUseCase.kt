package com.zalomsky.wallet.domain.usecase.account

import com.zalomsky.wallet.domain.model.AccountEntity
import com.zalomsky.wallet.domain.repository.AccountRepository
import com.zalomsky.wallet.domain.validator.AccountValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountValidator: AccountValidator
) {
    suspend operator fun invoke(accountEntity: AccountEntity): Result<Unit> =
        withContext(Dispatchers.IO) {
            accountValidator.invoke(accountEntity)
                .onFailure {
                    return@withContext Result.failure(it)
                }
            val result = runCatching {
                accountRepository.insertAccount(accountEntity = accountEntity)
            }

            result
        }
}