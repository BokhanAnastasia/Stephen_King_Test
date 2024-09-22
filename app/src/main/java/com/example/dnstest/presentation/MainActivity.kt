package com.example.dnstest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.dnstest.R
import com.example.dnstest.data.BooksRepositoryImpl
import com.example.dnstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        rcViewShowBooks()
    }

    /**
     * Настраивает элементы пользовательского интерфейса.
     */

    private fun setView(){
        //rcView
        adapter = BooksAdapter()
        binding.rcView.adapter = adapter

        //swipe refresh
        binding.swipeRefreshLayout.isRefreshing = false
        binding.swipeRefreshLayout
            .setColorSchemeResources(R.color.bloodRed)
        binding.swipeRefreshLayout
            .setProgressBackgroundColorSchemeResource(R.color.colorProgressBar)

        //view model
        viewModel = ViewModelProvider(
            this, ViewModelFactory(BooksRepositoryImpl))[MainViewModel::class.java]

    }

    /**
     * Устанавливает слушатель на swipeRefreshLayout, который вызывает
       метод получения книг по жесту pull to refresh.
     */

    private fun onSwipeRefreshAction(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getBooks(true)
        }
    }

    /**
     * Запрашивает и отображает список книг.
     * Получает данные из ViewModel
       и обновляет адаптер с новым списком книг.
     */

    private fun rcViewShowBooks(){
        viewModel.getBooks()
        loadingProgressBar()
        viewModel.books.observe(this){
            adapter.submitList(it)
        }
        onSwipeRefreshAction()
    }

    /**
     * Настраивает индикатор загрузки.
     * Наблюдает за состоянием загрузки в ViewModel и обновляет
       состояние swipeRefreshLayout в соответствии с этим.
     */

    private fun loadingProgressBar(){
        viewModel.isLoading.observe(this){
            binding.swipeRefreshLayout.isRefreshing = it
        }
    }

}