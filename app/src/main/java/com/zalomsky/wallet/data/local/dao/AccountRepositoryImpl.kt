package com.zalomsky.wallet.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zalomsky.wallet.domain.model.Account

@Dao
interface AccountRepositoryImpl {

    @Insert
    suspend fun insertAccount(account: Account)

    @Query("SELECT * FROM account")
    suspend fun getAllAccounts(): List<Account>

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("SELECT * FROM account WHERE id=:accountId")
    suspend fun getAccountById(accountId: Long): Account

}