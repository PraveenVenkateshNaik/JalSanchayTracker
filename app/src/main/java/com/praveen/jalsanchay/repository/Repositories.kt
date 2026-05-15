package com.praveen.jalsanchay.repository

import com.praveen.jalsanchay.data.local.dao.RainfallDao
import com.praveen.jalsanchay.data.local.dao.TipsDao
import com.praveen.jalsanchay.data.local.dao.UserDao
import com.praveen.jalsanchay.data.local.entity.FavoriteTip
import com.praveen.jalsanchay.data.local.entity.RainfallEntry
import com.praveen.jalsanchay.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserByUsername(username: String) = userDao.getUserByUsername(username)
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    fun getCurrentUser() = userDao.getCurrentUser()
    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)
}

class RainfallRepository @Inject constructor(
    private val rainfallDao: RainfallDao
) {
    suspend fun insertEntry(entry: RainfallEntry) = rainfallDao.insertEntry(entry)
    suspend fun deleteEntry(entry: RainfallEntry) = rainfallDao.deleteEntry(entry)
    fun getAllEntries(): Flow<List<RainfallEntry>> = rainfallDao.getAllEntries()
    fun getTotalWaterSaved(): Flow<Double?> = rainfallDao.getTotalWaterSaved()
    fun getLatestEntry(): Flow<RainfallEntry?> = rainfallDao.getLatestEntry()
}

class TipsRepository @Inject constructor(
    private val tipsDao: TipsDao
) {
    suspend fun insertTip(tip: FavoriteTip) = tipsDao.insertTip(tip)
    suspend fun deleteTip(tip: FavoriteTip) = tipsDao.deleteTip(tip)
    fun getAllFavoriteTips(): Flow<List<FavoriteTip>> = tipsDao.getAllFavoriteTips()
}
