package com.zalomsky.wallet.domain.repository

import com.zalomsky.wallet.data.local.dao.AccountDao
import com.zalomsky.wallet.domain.model.Account
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
){
    suspend fun getAllAccounts(): List<Account> = accountDao.getAllAccounts()
    suspend fun insertAccount(account: Account) = accountDao.insertAccount(account = account)
    suspend fun deleteAccount(account: Account) = accountDao.deleteAccount(account = account)
    suspend fun getAccountById(id: Long) = accountDao.getAccountById(accountId = id)
    suspend fun updateAccount(account: Account) = accountDao.updateAccount(account = account)
}