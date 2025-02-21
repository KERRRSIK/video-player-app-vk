package com.example.video_player_app_vk.data.local

import androidx.room.*
import com.example.video_player_app_vk.data.model.VideoEntity

@Dao
interface VideoDao {

    @Query("SELECT * FROM videos")
    suspend fun getAllVideos(): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoEntity>)

    @Query("DELETE FROM videos")
    suspend fun clearVideos()
}
