package com.example.tmdbdemo.data

import com.example.tmdbdemo.data.models.response.TrendingDataResponse
import com.example.tmdbdemo.utils.EndPoints
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(EndPoints.trending)
    fun trendingData(@Query("api_key") apiKey: String): Single<TrendingDataResponse>

    @GET(EndPoints.search)
    fun tmdbSearch(@Query("api_key") apiKey: String,@Query("query") query: String  ): Single<TrendingDataResponse>

}