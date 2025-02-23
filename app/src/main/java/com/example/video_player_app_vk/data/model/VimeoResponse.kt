package com.example.video_player_app_vk.data.model

data class VimeoResponse(val data: List<VimeoVideo>)

data class VimeoVideo(
    val id: String,
    val name: String,
    val duration: Int,
    val link: String,
    val pictures: Pictures
)

data class Pictures(val sizes: List<PictureSize>)
data class PictureSize(val link: String)
