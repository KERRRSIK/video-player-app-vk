package com.example.video_player_app_vk.data

import android.util.Log
import com.example.video_player_app_vk.data.local.VideoDao
import com.example.video_player_app_vk.data.model.VideoEntity
import com.example.video_player_app_vk.data.network.RetrofitClient
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val videoDao: VideoDao
) {

    suspend fun fetchVideos(): List<VideoEntity> {
        return try {
            val response = RetrofitClient.api.getVideos()
                Log.e("VideoRepository", "No trending videos found.")
                Log.e("VideoRepository", "Fetched ${response.data.size} videos from API.")
            val videos = response.data.map { video ->
                VideoEntity(
                    id = video.id,
                    title = video.name,
                    duration = video.duration,
                    thumbnailUrl = video.pictures.sizes.firstOrNull()?.link ?: "",
                    videoUrl = video.link
                )
            }
            videoDao.clearVideos()
            videoDao.insertVideos(videos)
            videos
        } catch (e: Exception) {
            videoDao.getAllVideos()
        }
    }
}
