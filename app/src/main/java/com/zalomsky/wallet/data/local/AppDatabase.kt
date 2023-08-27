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
    autoMigrations = [AutoMigration(from = 4, to = 5/*, spec = AppDatabase.Migration1To2::class*/)],
    version = 5
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountRepositoryImpl

    abstract fun categoryDao(): CategoryRepositoryImpl

/*    class Migration1To2: AutoMigrationSpec*/

/*    val MIGRATION_21_22: Migration = object : Migration(21, 22) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE 'Category' ('id' INTEGER, 'name' TEXT, 'icon' INTEGER, 'amount' REAL, PRIMARY KEY('id'))")
        }
    }*/


}