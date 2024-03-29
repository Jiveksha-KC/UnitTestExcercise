package com.unittestexcercise.repository

import com.unittestexcercise.networkapi.BookApi
import com.unittestexcercise.networkapi.model.BookResponse
import com.unittestexcercise.networkapi.model.UpdateAuthorRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val bookApi: BookApi
) {
    suspend fun getBooks(): List<BookResponse> = withContext(Dispatchers.IO) {
        bookApi.getBooks()
    }

    suspend fun getBook(id: Int): BookResponse = withContext(Dispatchers.IO) {
        bookApi.getBook(id)
    }

    suspend fun updateAuthor(id: Int, author: String) = withContext(Dispatchers.IO) {
        val requestBody = UpdateAuthorRequest(id, author)
        bookApi.updateBookAuthor(requestBody)
    }
}