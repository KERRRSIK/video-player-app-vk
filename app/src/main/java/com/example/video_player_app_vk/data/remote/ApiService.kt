package com.example.video_player_app_vk.data.remote

import com.example.video_player_app_vk.data.model.ClipResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("clips")
    suspend fun getTopClips(
        @Query("first") first: Int = 10,
        @Query("started_at") startedAt: String,
        @Query("game_id") gameId: String = "509672",
        @Header("Authorization") authToken: String,
        @Header("Client-Id") clientId: String
    ): Response<ClipResponse>
}