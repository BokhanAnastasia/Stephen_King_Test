package com.example.dnstest.data

import android.util.Log
import com.example.dnstest.domain.Book
import com.example.dnstest.domain.BooksRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BooksRepositoryImpl: BooksRepository{

    // Базовый URL для доступа к API книг.
    private const val BASE_URL = "https://stephen-king-api.onrender.com/api/"

    // Тег для логирования ошибок.
    private const val LOG_TAG = "BooksRepositoryImpl_Error"


    // Создание экземпляра API-сервиса с использованием Retrofit.
    private val apiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BooksApiService::class.java)


    /**
     * @return Список объектов Book, полученных из API.
     * Если произошла ошибка, в лог записывается сообщение об ошибке,
       а функция возвращает пустой список.
     */
    override suspend fun getBooks(): List<Book>  {
        return try {
            apiService.getBooksAPI().data.map {
                Book(id = it.id, Year = it.Year, Title = it.Title)
            }
        }catch (e: Exception) {
            Log.e(LOG_TAG, e.toString())
            emptyList()
        }
    }



}