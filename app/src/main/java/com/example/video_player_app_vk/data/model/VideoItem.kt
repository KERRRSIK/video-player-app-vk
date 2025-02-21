package com.example.video_player_app_vk.data.model

data class VideoItem(
    val id: Int,
    val url: String,
    val video_files: List<VideoFile>,
    val image: String, // Миниатюра
    val duration: Int  // Длительность в секундах
)
