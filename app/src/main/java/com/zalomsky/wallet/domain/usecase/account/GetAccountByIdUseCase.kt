package com.zalomsky.wallet.domain.usecase.account

import com.zalomsky.wallet.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountByIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(id: Long) = accountRepository.getAccountById(id = id)
}