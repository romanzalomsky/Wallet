package com.zalomsky.wallet.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zalomsky.wallet.domain.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {


    @Insert
    suspend fun insertAccount(account: Account)

    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<Account>>

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("SELECT * FROM account WHERE id=:accountId")
    suspend fun getAccountById(accountId: Long): Account

    @Update
    suspend fun updateAccount(account: Account)

}