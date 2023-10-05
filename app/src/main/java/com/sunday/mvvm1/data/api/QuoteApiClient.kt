package com.sunday.mvvm1.data.api

import com.sunday.mvvm1.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/.json")
    suspend fun getQuotes(): Response<List<QuoteModel>>
}