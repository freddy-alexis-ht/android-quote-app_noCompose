package com.sunday.mvvm1.domain

import com.sunday.mvvm1.data.QuoteRepository
import com.sunday.mvvm1.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
){
    suspend operator fun invoke(): Quote? {
        val quotes = repository.getQuotesFromRoom()
        if(!quotes.isNullOrEmpty()) {
            // val pos = (0..quotes.size-1).random()
            val pos = (quotes.indices).random()
            return quotes[pos]
        }
        return null
    }
}