package com.example.tmdbdemo.data.models.response

data class TrendingDataResponse(
    val page: Int,
    val results: List<ResultsItem>,
    val total_pages: Int,
    val total_results: Int
)