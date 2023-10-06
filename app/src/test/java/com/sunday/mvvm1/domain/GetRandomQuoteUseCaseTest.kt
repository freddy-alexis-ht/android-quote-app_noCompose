package com.sunday.mvvm1.domain

import com.sunday.mvvm1.data.QuoteRepository
import com.sunday.mvvm1.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking {
        // Given
        coEvery { quoteRepository.getQuotesFromRoom() } returns emptyList()
        // When
        val response = getRandomQuoteUseCase()
        // Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {
        // Given
        val myList = listOf(Quote("My quote", "Author"))
        coEvery { quoteRepository.getQuotesFromRoom() } returns myList
        // When
        val response = getRandomQuoteUseCase()
        // Then
        assert(response == myList.first())
    }
}