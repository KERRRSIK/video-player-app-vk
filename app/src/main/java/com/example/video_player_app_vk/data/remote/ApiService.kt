package com.example.video_player_app_vk.data.remote

import com.example.video_player_app_vk.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("videos/popular")
    suspend fun getPopularVideos(
        @Header("Authorization") apiKey: String,
        @Query("per_page") perPage: Int = 10
    ): VideoResponse
}