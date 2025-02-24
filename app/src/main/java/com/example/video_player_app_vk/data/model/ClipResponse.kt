package com.example.video_player_app_vk.data.model

data class ClipResponse(
    val data: List<Clip>
)

data class Clip(
    val id: String,
    val url: String,
    val embed_url: String,
    val broadcaster_id: String,
    val broadcaster_name: String,
    val title: String,
    val thumbnail_url: String,
    val duration: Float
)