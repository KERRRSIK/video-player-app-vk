package com.example.video_player_app_vk.data

import android.util.Log
import com.example.video_player_app_vk.data.model.VideoEntity
import com.example.video_player_app_vk.data.remote.ApiService
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


class VideoRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTopClips(authToken: String, clientId: String): List<VideoEntity> {
        return try {
            val oneWeekAgo = getOneWeekAgoDate()
            val response = apiService.getTopClips(
                first = 10,
                startedAt = oneWeekAgo,
                authToken = "Bearer $authToken",
                clientId = clientId
            )
            if (response.isSuccessful) {
                val clips = response.body()?.data ?: emptyList()
                Log.d("VideoRepository", "Loaded ${clips.size} clips")
                // преобразуем Clip в VideoEntity
                clips.map { clip ->
                    VideoEntity(
                        id = clip.id,
                        title = clip.title,
                        thumbnailUrl = clip.thumbnail_url,
                        duration = clip.duration.toInt(),
                        videoUrl = clip.url
                    )
                }
            } else {
                Log.e("VideoRepository", "API error: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("VideoRepository", "Request exception: ${e.message}")
            emptyList()
        }
    }

    private fun getOneWeekAgoDate(): String {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -7)
        }
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        return sdf.format(calendar.time)
    }
}