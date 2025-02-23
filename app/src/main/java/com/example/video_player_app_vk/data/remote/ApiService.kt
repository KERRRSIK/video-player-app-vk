package com.example.video_player_app_vk.data.remote

import com.example.video_player_app_vk.data.model.VimeoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("videos")
    suspend fun getVideos(
        @Header("Authorization") auth: String = "Bearer 902bbda660ed820c9c4fa8847fc5904c",
        @Query("filter") filter: String = "trending"
    ): VimeoResponse
}


