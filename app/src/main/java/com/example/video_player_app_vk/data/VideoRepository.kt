package com.example.video_player_app_vk.data


import android.util.Log
import com.example.video_player_app_vk.data.local.VideoDao
import com.example.video_player_app_vk.data.model.VideoEntity
import com.example.video_player_app_vk.data.model.VideoItem
import com.example.video_player_app_vk.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val apiService: ApiService,
    private val videoDao: VideoDao
) {

    suspend fun fetchVideos(apiKey: String): List<VideoItem> {
        return try {
            val response = apiService.getPopularVideos(apiKey = apiKey)
            val videoEntities = response.videos.map { video ->
                VideoEntity(video.id, video.url, video.image, video.video_files.firstOrNull()?.link ?: "", video.duration)
            }
            videoDao.clearVideos()
            videoDao.insertVideos(videoEntities)
            response.videos
        } catch (e: IOException) {
            Log.e("VideoRepository", "Ошибка сети: ${e.message}")
            videoDao.getAllVideos().map {
                VideoItem(it.id, it.image, it.videoUrl, it.duration)
            }
        } catch (e: HttpException) {
            Log.e("VideoRepository", "Ошибка API: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            Log.e("VideoRepository", "Неизвестная ошибка: ${e.message}")
            emptyList()
        }
    }
}
