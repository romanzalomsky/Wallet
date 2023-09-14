package com.zalomsky.wallet.di

import android.content.Context
import androidx.room.Room
import com.zalomsky.wallet.data.local.AppDatabase
import com.zalomsky.wallet.data.local.dao.AccountDao
import com.zalomsky.wallet.data.local.dao.CategoryDao
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
            .build()

    }

    @Provides
    fun providesAccountDao(appDatabase: AppDatabase): AccountDao{
        return appDatabase.accountDao()
    }

    @Provides
    fun providesCategoryDao(appDatabase: AppDatabase): CategoryDao{
        return appDatabase.categoryDao()
    }
}