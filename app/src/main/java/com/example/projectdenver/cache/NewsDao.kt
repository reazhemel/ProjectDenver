package com.example.projectdenver.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectdenver.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNewsEntity(article: List<NewsEntity>)

    @Query("SELECT * FROM news_table")
    fun getNewsList(): Flow<List<NewsEntity>>
}