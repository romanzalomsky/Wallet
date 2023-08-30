package com.zalomsky.wallet.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalomsky.wallet.data.local.dao.AccountRepositoryImpl
import com.zalomsky.wallet.data.local.dao.CategoryRepositoryImpl
import com.zalomsky.wallet.domain.model.Account
import com.zalomsky.wallet.domain.model.Category

@Database(
    entities = [Account::class, Category::class],
    autoMigrations = [AutoMigration(from = 7, to = 8/*, spec = AppDatabase.Migration9To10::class)*/)],
    version = 8
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountRepositoryImpl

    abstract fun categoryDao(): CategoryRepositoryImpl

/*    @DeleteColumn(tableName = "Account", columnName = "icon")*/
/*    class Migration9To10: AutoMigrationSpec*/

}