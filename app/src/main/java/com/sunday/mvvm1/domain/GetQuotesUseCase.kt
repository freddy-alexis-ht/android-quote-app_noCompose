package com.sunday.mvvm1.domain

import com.sunday.mvvm1.data.QuoteRepository
import com.sunday.mvvm1.data.database.entity.toDatabase
import com.sunday.mvvm1.data.model.QuoteModel
import com.sunday.mvvm1.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): List<Quote>? {
        // cuando se abre la app, se consulta la api
        val quotes = repository.getQuotesFromApi()
        if(quotes.isNotEmpty()) {
            // obtuvo la lista desde la api, borra la que existe
            repository.deleteQuotesInRoom()
            // inserta en db
            repository.insertQuotesInRoom(quotes.map { it.toDatabase() })
            return quotes
        } else {
            return repository.getQuotesFromRoom()
        }
    }
}