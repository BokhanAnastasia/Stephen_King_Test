package com.example.dnstest.domain


class GetBooksUseCase(private val repository: BooksRepository) {

    /**
    * Вохвращает список книг, выпущенных после 1990 года.
    */
    suspend fun getBooksAfter1990(): List<Book> = repository.getBooks()
        .filter { it.Year >= 1990 }
}