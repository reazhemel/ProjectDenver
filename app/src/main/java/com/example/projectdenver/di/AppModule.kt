package com.example.projectdenver.di

import android.content.Context
import androidx.room.Room
import com.example.projectdenver.cache.NewsDao
import com.example.projectdenver.cache.NewsDatabase
import com.example.projectdenver.data.NewsApiService
import com.example.projectdenver.data.NewsRemoteRepository
import com.example.projectdenver.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRemoteRepository(api: NewsApiService): NewsRemoteRepository = NewsRemoteRepository(api)

    @Provides
    @Singleton
    fun providesLocalRepository(
      @ApplicationContext context: Context
    ): NewsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).build()

    @Provides
    @Singleton
    fun providesNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}