package com.example.dnstest.domain

interface BooksRepository {
    /**
    * @return Список объектов Book.
    */
    suspend fun getBooks(): List<Book>
}