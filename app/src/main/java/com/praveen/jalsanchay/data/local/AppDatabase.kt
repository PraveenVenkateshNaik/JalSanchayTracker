package com.praveen.jalsanchay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.praveen.jalsanchay.data.local.dao.RainfallDao
import com.praveen.jalsanchay.data.local.dao.TipsDao
import com.praveen.jalsanchay.data.local.dao.UserDao
import com.praveen.jalsanchay.data.local.entity.FavoriteTip
import com.praveen.jalsanchay.data.local.entity.RainfallEntry
import com.praveen.jalsanchay.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, RainfallEntry::class, FavoriteTip::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val rainfallDao: RainfallDao
    abstract val tipsDao: TipsDao
}
