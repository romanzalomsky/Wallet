package com.zalomsky.wallet.domain.usecase.account

import com.zalomsky.wallet.domain.model.AccountEntity
import com.zalomsky.wallet.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(accountEntity: AccountEntity): Result<Unit> =
        withContext(Dispatchers.IO){
            val result = runCatching { accountRepository.deleteAccount(accountEntity) }
            result
        }
}