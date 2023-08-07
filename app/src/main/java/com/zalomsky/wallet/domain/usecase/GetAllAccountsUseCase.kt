package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.repository.AccountRepository
import javax.inject.Inject

class GetAllAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() = accountRepository.getAllAccounts()

}