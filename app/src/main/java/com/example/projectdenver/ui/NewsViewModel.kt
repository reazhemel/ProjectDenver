package com.example.projectdenver.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectdenver.cache.NewsEntity
import com.example.projectdenver.data.NewsLocalRepository
import com.example.projectdenver.data.NewsRemoteRepository
import com.example.projectdenver.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository,
    private val newsLocalRepository: NewsLocalRepository
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<NewsEntity>>(emptyList())
    val newsList = _newsList.asStateFlow()

    init {
        getNewsFromApi()
        getNewsFromLocalApi()
    }

    private fun getNewsFromApi() {
        viewModelScope.launch {
            newsRemoteRepository.getPosts()
                .flowOn(Dispatchers.IO)
                .catch { exception -> }
                .collect { news ->
                    news.articles?.let {
                        newsLocalRepository.insertNewsList(
                            news.articles.map {
                                NewsEntity(
                                    author = it.author ?:"",
                                    title = it.title ?:"",
                                    description = it.description ?:""
                                )
                            }
                        )
                    }
                }
        }
    }

    private fun getNewsFromLocalApi(){
        viewModelScope.launch {
            newsLocalRepository.getNewsList().collect{
                _newsList.value = it
            }
        }
    }

}
