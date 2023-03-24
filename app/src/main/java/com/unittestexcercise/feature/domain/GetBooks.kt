package com.unittestexcercise.feature.domain

import com.unittestexcercise.domain.UseCaseResult
import com.unittestexcercise.feature.model.Book
import com.unittestexcercise.feature.mapper.toBook
import com.unittestexcercise.repository.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetBooks @Inject constructor(
    private val booksRepository: BooksRepository
) {

    suspend operator fun invoke(): UseCaseResult<List<Book>> = withContext(Dispatchers.Default) {
        try {
            val books = booksRepository.getBooks().map { it.toBook() }
            UseCaseResult.SuccessResult(books)
        } catch (e: IOException) {
            UseCaseResult.NetworkErrorResult
        } catch (e: Exception) {
            UseCaseResult.GenericErrorResult
        }
    }
}