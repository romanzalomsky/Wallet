package com.zalomsky.wallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalomsky.wallet.data.local.dao.AccountDao
import com.zalomsky.wallet.data.local.dao.CategoryDao
import com.zalomsky.wallet.domain.model.AccountEntity
import com.zalomsky.wallet.domain.model.Category

@Database(
    entities = [AccountEntity::class, Category::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountDao

    abstract fun categoryDao(): CategoryDao
}