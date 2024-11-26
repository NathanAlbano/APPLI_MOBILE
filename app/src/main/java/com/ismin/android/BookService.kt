package com.ismin.android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface BookService {
    @GET("books")
    fun getBooks(): Call<List<Book>>

    @GET("books/{bookId}")
    fun getBook(@Path("bookId") bookId: String): Call<Book>
}
