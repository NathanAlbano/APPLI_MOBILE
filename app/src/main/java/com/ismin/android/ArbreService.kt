package com.ismin.android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ArbreService {
    @GET("arbres")
    fun getArbres(): Call<List<Arbre>>

    @GET("arbres/{arbreId}")
    fun getArbre(@Path("arbreId") arbreId: String): Call<Arbre>
}
