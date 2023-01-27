package com.unittestexcercise.feature.domain

import com.unittestexcercise.domain.UseCaseResult
import com.unittestexcercise.feature.model.Book
import com.kinandcarta.unittestexercises.feature.mapper.toBook
import com.unittestexcercise.repository.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetBook @Inject constructor(
    private val booksRepository: BooksRepository
) {

    suspend operator fun invoke(id: Int): UseCaseResult<Book> = withContext(Dispatchers.Default) {
        try {
            val book = booksRepository.getBook(id).toBook()
            UseCaseResult.SuccessResult(book)
        } catch (e: IOException) {
            UseCaseResult.NetworkErrorResult
        } catch (e: Exception) {
            UseCaseResult.GenericErrorResult
        }
    }
}