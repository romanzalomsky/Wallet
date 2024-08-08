package com.zalomsky.wallet.domain.usecase.account

import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(): Flow<List<Account>> = accountRepository.getAllAccounts()
}