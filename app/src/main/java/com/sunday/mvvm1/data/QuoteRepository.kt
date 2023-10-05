package com.sunday.mvvm1.data

import com.sunday.mvvm1.data.api.QuoteService
import com.sunday.mvvm1.data.database.dao.QuoteDao
import com.sunday.mvvm1.data.database.entity.QuoteEntity
import com.sunday.mvvm1.data.model.QuoteModel
import com.sunday.mvvm1.domain.model.Quote
import com.sunday.mvvm1.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes()
        val response2: List<Quote> = response.map { it.toDomain() }
        return response2
    }

    suspend fun getQuotesFromRoom(): List<Quote> {
        val response: List<QuoteEntity> = quoteDao.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotesInRoom(quotes: List<QuoteEntity>) {
        quoteDao.insertQuotes(quotes)
    }

    suspend fun deleteQuotesInRoom() {
        quoteDao.deleteQuotes()
    }
}