package com.zalomsky.wallet.domain.usecase.account

import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: Account): Result<Unit> =
        withContext(Dispatchers.IO){
            val result = runCatching { accountRepository.deleteAccount(account) }
            result
        }
}