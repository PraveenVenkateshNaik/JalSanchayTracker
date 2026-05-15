package com.praveen.jalsanchay.di

import android.content.Context
import androidx.room.Room
import com.praveen.jalsanchay.data.datastore.UserPreferences
import com.praveen.jalsanchay.data.local.AppDatabase
import com.praveen.jalsanchay.data.local.dao.RainfallDao
import com.praveen.jalsanchay.data.local.dao.TipsDao
import com.praveen.jalsanchay.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "jalsanchay_db"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao

    @Provides
    fun provideRainfallDao(appDatabase: AppDatabase): RainfallDao = appDatabase.rainfallDao

    @Provides
    fun provideTipsDao(appDatabase: AppDatabase): TipsDao = appDatabase.tipsDao

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }
}
