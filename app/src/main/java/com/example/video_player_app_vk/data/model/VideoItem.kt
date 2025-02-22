package com.example.video_player_app_vk.data.model

data class VideoItem(
    val id: Int,
    val image: String, // Миниатюра
    val video_files: List<VideoFile>,
    val duration: Int,  // Длительность в секундах
    val url: String,
    val title: String = "Video $id"
)
