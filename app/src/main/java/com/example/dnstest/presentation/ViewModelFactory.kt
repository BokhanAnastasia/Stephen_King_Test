package com.example.dnstest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dnstest.domain.BooksRepository

 /**
  * Фабрика для создания экземпляров ViewModel.
  * Реализует интерфейс ViewModelProvider.Factory.
  *
  * @property repository Репозиторий, предоставляющий доступ к данным о книгах.
  */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BooksRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}