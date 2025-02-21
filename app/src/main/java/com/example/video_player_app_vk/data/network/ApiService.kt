package com.example.video_player_app_vk.data.network

import com.example.video_player_app_vk.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("videos/popular")
    suspend fun getPopularVideo(
        @Query("per_page") perPage: Int = 10,
        @Header("Authorization") apiKey: String
    ): VideoResponse
}