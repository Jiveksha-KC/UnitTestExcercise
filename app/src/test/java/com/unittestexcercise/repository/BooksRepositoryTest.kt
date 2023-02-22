package com.unittestexcercise.repository

import com.google.common.truth.Truth.assertThat
import com.unittestexcercise.MockKRule
import com.unittestexcercise.networkapi.BookApi
import com.unittestexcercise.networkapi.model.BookResponse
import com.unittestexcercise.stubs.BookResponseStub
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BooksRepositoryTest {

    private companion object {
        val SOME_BOOK = BookResponseStub.new()
        val SOME_BOOKS = listOf(SOME_BOOK)
        val SOME_EMPTY_LIST: List<BookResponse> = emptyList()
    }

    @get: Rule
    val mockkRule = MockKRule()

    @MockK
    private lateinit var mockBookApi: BookApi

    @InjectMockKs
    private lateinit var subject: BooksRepository

    @Before
    fun setup() {
        // GIVEN
        coEvery { mockBookApi.getBooks() } returns SOME_BOOKS
    }

    @Test
    fun `getBooks() - THEN books is returned from book api`() {
        // WHEN
        val result = runBlocking { subject.getBooks() }

        // THEN
        assertThat(result).isEqualTo(SOME_BOOKS)
    }

    @Test
    fun `getBooks() with empty list - THEN no books returned`() {
        // GIVEN
        coEvery { mockBookApi.getBooks() } returns SOME_EMPTY_LIST

        // WHEN
        val result = runBlocking { subject.getBooks() }

        // THEN
        assertThat(result).isEmpty()
    }

    @Test
    fun `getBook() - THEN book returned from book api`() {
        // GIVEN
        coEvery { mockBookApi.getBook(SOME_BOOK.id) } returns SOME_BOOK

        // WHEN
        val result = runBlocking { subject.getBook(SOME_BOOK.id) }

        // THEN
        // access properties of the response
//        with(SOME_BOOK) {
//            assertThat(id).isEqualTo(id)
//            assertThat(result).isEqualTo(this)
//        }
        assertThat(result).isEqualTo(SOME_BOOK)
    }
}