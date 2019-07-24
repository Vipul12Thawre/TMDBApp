package com.example.tmdbdemo.data.repository

import com.example.tmdbdemo.data.NetworkService
import com.example.tmdbdemo.data.Networking
import com.example.tmdbdemo.data.models.response.ResultsItem
import io.reactivex.Single
import javax.inject.Inject

class TMDBRepository @Inject constructor(private val networkService: NetworkService) {

    fun fetchTrendingData(apiKey:String): Single<List<ResultsItem>> =
        networkService.trendingData(apiKey).map {
            it.results
        }

    fun fetchTmdbSearch(apiKey: String,query:String): Single<List<ResultsItem>> =
        networkService.tmdbSearch(apiKey,query).map {
            it.results
        }
}