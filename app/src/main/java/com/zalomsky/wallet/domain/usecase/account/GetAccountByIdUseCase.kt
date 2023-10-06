package com.zalomsky.wallet.domain.usecase.account

import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAccountByIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(id: Long): Flow<Result<Account>> =
        accountRepository.getAllAccounts().map { accounts ->
            val account = accounts.first {it.id == id}

            val result = Result.success(account)

            result
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
}