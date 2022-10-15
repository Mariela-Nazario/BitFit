package com.mnazari2.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM article_table")
    fun getAll(): Flow<List<foodCaloriesEntity>>

    @Insert
    fun insertAll(articles: foodCaloriesEntity)

    @Query("DELETE FROM article_table")
    fun deleteAll()
}