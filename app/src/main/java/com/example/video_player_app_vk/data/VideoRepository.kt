package com.example.video_player_app_vk.data

import android.util.Log
import com.example.video_player_app_vk.data.local.VideoDao
import com.example.video_player_app_vk.data.model.VideoEntity
import com.example.video_player_app_vk.data.model.VideoItem
import com.example.video_player_app_vk.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val apiService: ApiService,
    private val videoDao: VideoDao
) {

    suspend fun fetchVideos(apiKey: String): List<VideoItem> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getPopularVideos(apiKey = apiKey)
            // Преобразуем данные для Room
            val entities = response.videos.map { video ->
                VideoEntity(
                    id = video.id,
                    image = video.image,
                    url = video.video_files.firstOrNull()?.link ?: "",
                    duration = video.duration,
                    title = video.title
                )
            }
            videoDao.clearVideos()
            videoDao.insertVideos(entities)
            response.videos
        } catch (e: IOException) {
            Log.e("VideoRepository", "Ошибка сети: ${e.message}")
            // Если нет интернета, загружаем из кеша
            videoDao.getAllVideos().map {
                VideoItem(
                    id = it.id,
                    image = it.image,
                    video_files = listOf(),  // отсутствуют подробности о файлах – можно доработать
                    duration = it.duration,
                    url = it.url,
                    title = it.title
                )
            }
        } catch (e: Exception) {
            Log.e("VideoRepository", "Ошибка: ${e.message}")
            emptyList()
        }
    }
}
