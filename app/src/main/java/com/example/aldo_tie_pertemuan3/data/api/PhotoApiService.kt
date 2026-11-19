package com.example.aldo_tie_pertemuan3.data.api

import com.example.aldo_tie_pertemuan3.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}