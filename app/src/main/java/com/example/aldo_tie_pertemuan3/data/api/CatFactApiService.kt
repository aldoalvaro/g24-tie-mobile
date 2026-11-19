package com.example.aldo_tie_pertemuan3.data.api

import com.example.aldo_tie_pertemuan3.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}