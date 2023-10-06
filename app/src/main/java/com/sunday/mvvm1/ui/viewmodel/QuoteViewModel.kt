package com.sunday.mvvm1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.mvvm1.data.model.QuoteModel
import com.sunday.mvvm1.domain.GetQuotesUseCase
import com.sunday.mvvm1.domain.GetRandomQuoteUseCase
import com.sunday.mvvm1.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    private val _quote = MutableLiveData<Quote>()
    val quote:LiveData<Quote> = _quote
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getQuotesUseCase()
            if(!result.isNullOrEmpty()) {
                _quote.postValue(result[0])
                _isLoading.postValue(false)
            }
        }
    }
    fun updateQuote() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            if(quote != null) {
                _quote.postValue(quote!!)
            }
            _isLoading.postValue(false)
        }
    }

    fun setQuote(quote: Quote) {
        this._quote.value = quote
    }
}