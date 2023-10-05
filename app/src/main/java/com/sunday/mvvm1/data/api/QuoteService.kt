package com.sunday.mvvm1.data.api

import com.sunday.mvvm1.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val apiClient: QuoteApiClient
) {

    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
//            val response = retrofit.create(QuoteApiClient::class.java).getQuotes()
            val response = apiClient.getQuotes()
            response.body().orEmpty()
        }
    }
}