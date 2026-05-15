package com.praveen.jalsanchay.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.praveen.jalsanchay.data.local.entity.FavoriteTip
import com.praveen.jalsanchay.data.local.entity.RainfallEntry
import com.praveen.jalsanchay.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): Flow<UserEntity?>
}

@Dao
interface RainfallDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: RainfallEntry)

    @Delete
    suspend fun deleteEntry(entry: RainfallEntry)

    @Query("SELECT * FROM rainfall_entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<RainfallEntry>>

    @Query("SELECT SUM(litersSaved) FROM rainfall_entries")
    fun getTotalWaterSaved(): Flow<Double?>
    
    @Query("SELECT * FROM rainfall_entries ORDER BY date DESC LIMIT 1")
    fun getLatestEntry(): Flow<RainfallEntry?>
}

@Dao
interface TipsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTip(tip: FavoriteTip)

    @Delete
    suspend fun deleteTip(tip: FavoriteTip)

    @Query("SELECT * FROM favorite_tips")
    fun getAllFavoriteTips(): Flow<List<FavoriteTip>>
}
