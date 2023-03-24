package com.unittestexcercise.feature.mapper

import com.unittestexcercise.feature.model.Book
import com.unittestexcercise.networkapi.model.BookResponse

fun BookResponse.toBook() = Book(
    id = id,
    title = title,
    author = author
)