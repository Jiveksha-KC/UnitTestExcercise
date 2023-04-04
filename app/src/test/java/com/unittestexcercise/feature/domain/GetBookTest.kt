package com.unittestexcercise.feature.domain

import com.google.common.truth.Truth.assertThat
import com.unittestexcercise.MockKRule
import com.unittestexcercise.domain.UseCaseResult
import com.unittestexcercise.feature.mapper.toBook
import com.unittestexcercise.feature.model.Book
import com.unittestexcercise.networkapi.model.BookResponse
import com.unittestexcercise.repository.BooksRepository
import com.unittestexcercise.stubs.BookResponseStub
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import java.io.IOException
import java.lang.Exception
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetBookTest {

    companion object {
        const val SOME_BOOK_ID = 1
        val SOME_BOOK_RESPONSE: BookResponse = mockk()
        val SOME_BOOK: Book = mockk()
    }

    @get: Rule
    val mockkRule = MockKRule()

    @MockK
    private lateinit var mockBooksRepository: BooksRepository

    @InjectMockKs
    private lateinit var subject: GetBook

    @Before
    fun setup() {
        mockkStatic(BookResponse::toBook)
        every { SOME_BOOK_RESPONSE.toBook() } returns SOME_BOOK
    }

    @Test
    fun `invoke() - GIVEN booksRepository getBook is success THEN return SuccessResult`() {
        // GIVEN
        coEvery { mockBooksRepository.getBook(SOME_BOOK_ID) } returns SOME_BOOK_RESPONSE

        // WHEN
        val result = runBlocking { subject(SOME_BOOK_ID) }

        // THEN
        assertThat(result).isEqualTo(UseCaseResult.SuccessResult(SOME_BOOK))
    }

    @Test
    fun `invoke() - GIVEN booksRepository getBook is network error THEN return NetworkErrorResult`() {
        // GIVEN
        coEvery { mockBooksRepository.getBook(SOME_BOOK_ID) } throws IOException()

        // WHEN
        val result = runBlocking { subject(SOME_BOOK_ID) }

        // THEN
        assertThat(result).isEqualTo(UseCaseResult.NetworkErrorResult)
    }

    @Test
    fun `invoke() - GIVEN booksRepository getBook is generic error THEN return GenericErrorResult`() {
        // GIVEN
        coEvery { mockBooksRepository.getBook(SOME_BOOK_ID) } throws Exception()

        // WHEN
        val result = runBlocking { subject(SOME_BOOK_ID) }

        // THEN
        assertThat(result).isEqualTo(UseCaseResult.GenericErrorResult)
    }
}