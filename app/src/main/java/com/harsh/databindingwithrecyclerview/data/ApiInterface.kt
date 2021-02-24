package com.harsh.databindingwithrecyclerview.data

import com.harsh.databindingwithrecyclerview.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

object APICONSTANT {
    const val HEADLINES = "top-headlines"
}

interface ApiInterface {

    //    https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=YOUR_API_KEY
    @GET(APICONSTANT.HEADLINES)
    suspend fun getHeadLines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse<ArrayList<Article>>
}