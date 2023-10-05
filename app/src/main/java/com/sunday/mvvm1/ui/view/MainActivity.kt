package com.sunday.mvvm1.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.sunday.mvvm1.databinding.ActivityMainBinding
import com.sunday.mvvm1.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteVM: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteVM.onCreate()

        /* ACCIÓN DEL USUARIO EN EL VIEW .. QUE CAMBIARÁ EL MODEL */
        binding.clContainer.setOnClickListener {
            quoteVM.updateQuote()
        }

        /* CAMBIADO EL MODEL .. SE NOTIFICA AL VIEW PARA QUE SE MODIFIQUE */
        quoteVM.quote.observe(this, Observer { // it: Quote
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        })
        quoteVM.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }
}