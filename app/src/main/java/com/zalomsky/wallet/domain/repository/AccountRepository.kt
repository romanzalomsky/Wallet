package com.zalomsky.wallet.domain.repository

import com.zalomsky.wallet.data.local.dao.AccountRepositoryImpl
import com.zalomsky.wallet.domain.model.Account
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountRepositoryImpl: AccountRepositoryImpl
){
    suspend fun getAllAccounts(): List<Account> = accountRepositoryImpl.getAllAccounts()
    suspend fun insertAccount(account: Account) = accountRepositoryImpl.insertAccount(account = account)
    suspend fun deleteAccount(account: Account) = accountRepositoryImpl.deleteAccount(account = account)
    suspend fun getAccountById(id: Long) = accountRepositoryImpl.getAccountById(accountId = id)
}