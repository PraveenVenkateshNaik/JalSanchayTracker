package com.praveen.jalsanchay.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val passwordHash: String,
    val totalSavings: Double = 0.0,
    val ecoScore: Int = 0
)

@Entity(tableName = "rainfall_entries")
data class RainfallEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val roofArea: Double,
    val rainfall: Double,
    val runoffCoefficient: Double,
    val litersSaved: Double,
    val tankCapacity: Double,
    val tankLevel: Double // Percentage
)

@Entity(tableName = "favorite_tips")
data class FavoriteTip(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)
