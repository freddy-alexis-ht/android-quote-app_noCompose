package com.sunday.mvvm1.domain.model

import com.sunday.mvvm1.data.database.entity.QuoteEntity
import com.sunday.mvvm1.data.model.QuoteModel

data class Quote (
    val quote: String,
    val author: String
)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)

