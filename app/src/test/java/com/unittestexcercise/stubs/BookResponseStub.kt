package com.unittestexcercise.stubs

import com.unittestexcercise.networkapi.model.BookResponse

object BookResponseStub {
    const val ID = 1
    const val TITLE = "title"
    const val CAPTION = "caption"
    const val AUTHOR = "author"

    fun new(id: Int = ID,
            title: String = TITLE,
            caption: String = CAPTION,
            author: String = AUTHOR
    ) = BookResponse(id, title, caption, author)
}

