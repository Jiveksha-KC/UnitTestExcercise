package com.unittestexcercise.domain

import com.google.common.truth.Truth.assertThat
import com.unittestexcercise.MockKRule
import com.unittestexcercise.feature.domain.GetBooks
import com.unittestexcercise.feature.mapper.toBook
import com.unittestexcercise.feature.model.Book
import com.unittestexcercise.networkapi.model.BookResponse
import com.unittestexcercise.repository.BooksRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetBooksTest {

    private companion object {
        val SOME_BOOK_RESPONSE: BookResponse = mockk()
        val SOME_BOOKS_RESPONSE = listOf(SOME_BOOK_RESPONSE)
        val SOME_BOOK: Book = mockk()
    }

    @get: Rule
    val mockkRule = MockKRule()

    @MockK
    private lateinit var mockBooksRepository: BooksRepository

    @InjectMockKs
    private lateinit var subject: GetBooks

    @Before
    fun setup() {
        mockkStatic(BookResponse::toBook)
        every { SOME_BOOK_RESPONSE.toBook() } returns SOME_BOOK
    }

    @Test
    fun `invoke() - GIVEN booksRepository getBooks returns Success, THEN SuccessResult`() {
        // GIVEN
        coEvery { mockBooksRepository.getBooks() } returns SOME_BOOKS_RESPONSE

        // WHEN
        val result = runBlocking { subject() }

        // THEN
        assertThat(result).isEqualTo(UseCaseResult.SuccessResult(listOf(SOME_BOOK)))
    }

    @Test
    fun `invoke() - GIVEN booksRepository getBooks returns IOException, THEN NetworkErrorResult`() {
        // GIVEN
        coEvery { mockBooksRepository.getBooks() } throws IOException()

        // WHEN
        val result = runBlocking { subject() }

        // THEN
        assertThat(result).isEqualTo(UseCaseResult.NetworkErrorResult)
    }

    @Test
    fun `invoke() - GIVEN booksRepository getBooks returns Exception, THEN GenericErrorResult`() {
        // GIVEN
        coEvery { mockBooksRepository.getBooks() } throws Exception()

        // WHEN
        val result = runBlocking { subject() }

        // THEN
        assertThat(result).isEqualTo(UseCaseResult.GenericErrorResult)
    }
}