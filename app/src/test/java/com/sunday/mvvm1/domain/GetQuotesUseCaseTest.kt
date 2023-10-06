package com.sunday.mvvm1.domain

import com.sunday.mvvm1.data.QuoteRepository
import com.sunday.mvvm1.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    // Solo declaración, la instanciación la hace Mockk
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    // Se declara, se instancia luego
    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this) // inicializa Mockk
        getQuotesUseCase = GetQuotesUseCase(quoteRepository) // instancia el CU
    }

    @Test
//    fun whenApiDoesntReturnAnythingThenGetValuesFromDatabase()
    fun `when api doesnt return anything then get values from database`() = runBlocking {
        // Given
        coEvery { quoteRepository.getQuotesFromApi() } returns emptyList()

        // When
        getQuotesUseCase()

        // Then
        coVerify(exactly = 1) { quoteRepository.getQuotesFromRoom() }
    }

    @Test
    fun `when api does return the list of quotes`() = runBlocking {
        // Given
        val myList = listOf(Quote("My quote", "Author"))
        coEvery { quoteRepository.getQuotesFromApi() } returns myList

        // When
        val response = getQuotesUseCase()

        // Then
        coVerify(exactly = 1) { quoteRepository.deleteQuotesInRoom() }
        coVerify(exactly = 1) { quoteRepository.insertQuotesInRoom(any()) }
        coVerify(exactly = 0) { quoteRepository.getQuotesFromRoom() }
        assert(myList == response)
    }
}