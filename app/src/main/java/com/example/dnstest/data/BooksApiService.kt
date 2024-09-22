package com.example.dnstest.data

import retrofit2.http.GET

interface BooksApiService {
    @GET("books")
    suspend fun getBooksAPI(): ApiResponse
}