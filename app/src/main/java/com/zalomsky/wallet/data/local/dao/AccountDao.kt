package com.zalomsky.wallet.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zalomsky.wallet.domain.model.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    suspend fun insertAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM account_table")
    fun getAllAccounts(): Flow<List<AccountEntity>>

    @Delete
    suspend fun deleteAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM account_table WHERE id=:accountId")
    suspend fun getAccountById(accountId: Long): AccountEntity

    @Update
    suspend fun updateAccount(accountEntity: AccountEntity)
}