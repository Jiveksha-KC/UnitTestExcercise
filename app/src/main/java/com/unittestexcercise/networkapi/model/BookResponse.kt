package com.unittestexcercise.networkapi.model

data class BookResponse(
    val id: Int,
    val title: String,
    val caption: String?,
    val author: String
)
