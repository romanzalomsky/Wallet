package com.zalomsky.wallet.domain.usecase

import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.repository.AccountRepository
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: Account) {
        accountRepository.insertAccount(account = account)
    }

}