package com.example.video_player_app_vk.data.model

data class VideoItem(
    val id: String,
    val title: String,
    val thumbnail_240_url: String,
    val duration: Int,
    val stream_h264_url: String? // Теперь это `hls`
)
