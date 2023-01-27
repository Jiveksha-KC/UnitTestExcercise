package com.unittestexcercise.networkapi

import com.unittestexcercise.networkapi.model.BookResponse

interface BookApi {

    suspend fun getBooks(): List<BookResponse>

    suspend fun getBook(id: Int): BookResponse
}