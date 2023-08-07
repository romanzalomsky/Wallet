package com.zalomsky.wallet.di

import android.content.Context
import androidx.room.Room
import com.zalomsky.wallet.data.local.AppDatabase
import com.zalomsky.wallet.data.local.dao.AccountRepositoryImpl
import com.zalomsky.wallet.data.local.dao.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class HiltModule {

    @Provides
    fun provideAppDate(@ApplicationContext appContext: Context): AppDatabase{

        return Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database")
/*            .addMigrations(MIGRATION_21_22)*/
            .build()

    }

/*    val MIGRATION_21_22: Migration = object : Migration(21, 22) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE 'Category' ('id' INTEGER, 'name' TEXT, 'icon' INTEGER, 'amount' REAL, PRIMARY KEY('id'))")
        }
    }*/

    @Provides
    fun providesAccountDao(appDatabase: AppDatabase): AccountRepositoryImpl{
        return appDatabase.accountDao()
    }

    @Provides
    fun providesCategoryDao(appDatabase: AppDatabase): CategoryRepositoryImpl{
        return appDatabase.categoryDao()
    }
}