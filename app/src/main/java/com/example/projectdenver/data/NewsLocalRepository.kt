package com.example.projectdenver.data

import com.example.projectdenver.cache.NewsDao
import com.example.projectdenver.cache.NewsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalRepository @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun insertNewsList(article: List<NewsEntity>){
        newsDao.insertNewsEntity(article)
    }

    fun getNewsList(): Flow<List<NewsEntity>>{
        return newsDao.getNewsList()
    }
}