package com.example.video_player_app_vk.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.video_player_app_vk.data.VideoRepository
import com.example.video_player_app_vk.data.model.VideoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _videoState = MutableStateFlow<List<VideoEntity>>(emptyList())
    val videoState: StateFlow<List<VideoEntity>> get() = _videoState

    fun loadVideos() {
        viewModelScope.launch {
            _videoState.value = repository.fetchVideos()
        }
    }
}
