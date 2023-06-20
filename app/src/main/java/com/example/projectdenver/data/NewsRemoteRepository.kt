package com.example.projectdenver.data

import com.example.projectdenver.models.NewsResponseApi
import com.example.projectdenver.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRemoteRepository @Inject constructor(
    private val api: NewsApiService
) {
    fun getPosts(): Flow<NewsResponseApi> = flow {
        emit(
            api.getPosts(
                country = "us",
                category = "business",
                apiKey = Constants.API_KEY
            )
        )
    }
}