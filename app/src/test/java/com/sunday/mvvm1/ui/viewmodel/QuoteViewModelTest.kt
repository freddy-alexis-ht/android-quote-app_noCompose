package com.sunday.mvvm1.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sunday.mvvm1.data.QuoteRepository
import com.sunday.mvvm1.domain.GetQuotesUseCase
import com.sunday.mvvm1.domain.GetRandomQuoteUseCase
import com.sunday.mvvm1.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    // androidx.arch.core.executor.testing.InstantTaskExecutorRule
    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created first time then get all quotes and set the first one`() = runTest {
        // Given
        val myList = listOf(Quote("My quote", "Author"), Quote("My quote2", "Author2"))
        coEvery { getQuotesUseCase() } returns myList
        // When
        quoteViewModel.onCreate()
        // Then
        assert(quoteViewModel.quote.value == myList.first())
    }

    @Test
    fun `when randomQuoteUseCase returns a quote then set it on livedata`() = runTest {
        // Given
        val quote = Quote("My quote", "Author")
        coEvery { getRandomQuoteUseCase() } returns quote
        // When
        quoteViewModel.updateQuote()
        // Then
        assert(quoteViewModel.quote.value == quote)
    }

    @Test
    fun `when randomQuoteUseCase returns a null quote then keep last value`() = runTest {
        // Given
        val quote = Quote("My quote", "Author")
        quoteViewModel.setQuote(quote)
        coEvery { getRandomQuoteUseCase() } returns null
        // When
        quoteViewModel.updateQuote()
        // Then
        assert(quoteViewModel.quote.value == quote)
    }
}