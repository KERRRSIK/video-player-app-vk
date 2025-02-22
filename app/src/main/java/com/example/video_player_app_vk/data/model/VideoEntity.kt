package com.example.video_player_app_vk.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: Int,
    val image: String,
    val url: String,
    val duration: Int,
    val title: String
)
