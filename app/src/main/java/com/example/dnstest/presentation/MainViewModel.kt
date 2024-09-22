package com.example.dnstest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.dnstest.domain.Book
import com.example.dnstest.domain.BooksRepository
import com.example.dnstest.domain.GetBooksUseCase
import kotlinx.coroutines.launch


class MainViewModel(repository: BooksRepository) : ViewModel() {


    private val getBooksUseCase = GetBooksUseCase(repository)
    private var isBookLoaded = false

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    /**
     * Функция для получения списка книг
     *
     * @param hasToBeRefreshed Указывает, нужно ли принудительно обновить данные,
      если они ранее были загружены.
     */
    fun getBooks(hasToBeRefreshed: Boolean = false) {
        if (!isBookLoaded || hasToBeRefreshed) {
            _isLoading.value = true
            launchBooks()
        }
    }

    /**
     * Функция для запуска процесса получения данных о книгах.
     */
    private fun launchBooks(){
        viewModelScope.launch {
            val booksData = getBooksUseCase.getBooksAfter1990()

            if (booksData.isEmpty()) _books.value = _books.value
            else                     _books.value = booksData

            _isLoading.value = false
            isBookLoaded = true

        }
    }
}