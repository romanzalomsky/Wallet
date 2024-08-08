package com.zalomsky.wallet.domain.repository

import com.zalomsky.wallet.data.local.dao.AccountDao
import com.zalomsky.wallet.domain.model.AccountEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {

    fun getAllAccounts(): Flow<List<AccountEntity>> {
        return accountDao.getAllAccounts()
    }

    suspend fun insertAccount(accountEntity: AccountEntity) {
        accountDao.insertAccount(accountEntity = accountEntity)
    }

    suspend fun deleteAccount(accountEntity: AccountEntity) {
        accountDao.deleteAccount(accountEntity = accountEntity)
    }

    suspend fun updateAccount(accountEntity: AccountEntity) {
        accountDao.updateAccount(accountEntity = accountEntity)
    }
}