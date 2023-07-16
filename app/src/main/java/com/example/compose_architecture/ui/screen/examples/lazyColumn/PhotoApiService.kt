package com.example.compose_architecture.ui.screen.examples.lazyColumn

import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(
        @Query("page") pageNumber: Int,
        @Query("limit") limit: Int = 20
    ): List<Photo>
}