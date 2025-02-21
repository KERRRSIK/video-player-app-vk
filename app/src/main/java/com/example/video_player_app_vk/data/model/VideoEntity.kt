package com.example.video_player_app_vk.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val image: String,
    val videoUrl: String,
    val duration: Int
)
