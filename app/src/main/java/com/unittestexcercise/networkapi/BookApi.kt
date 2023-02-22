package com.unittestexcercise.networkapi

import com.unittestexcercise.networkapi.model.BookResponse
import com.unittestexcercise.networkapi.model.UpdateAuthorRequest

interface BookApi {

    suspend fun getBooks(): List<BookResponse>

    suspend fun getBook(id: Int): BookResponse

    suspend fun updateBookAuthor(updateAuthorRequest: UpdateAuthorRequest)
}