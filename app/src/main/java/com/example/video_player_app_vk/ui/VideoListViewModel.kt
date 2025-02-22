package com.example.video_player_app_vk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.video_player_app_vk.data.VideoRepository
import com.example.video_player_app_vk.data.model.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class VideoState {
    object Loading : VideoState()
    data class Success(val videos: List<VideoItem>) : VideoState()
    data class Error(val message: String) : VideoState()
}

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _videoState = MutableLiveData<VideoState>()
    val videoState: LiveData<VideoState> = _videoState

    fun loadVideos() {
        viewModelScope.launch {
            _videoState.value = VideoState.Loading
            try {
                val videos = repository.fetchVideos("YOUR_API_KEY")
                if (videos.isNotEmpty())
                    _videoState.value = VideoState.Success(videos)
                else
                    _videoState.value = VideoState.Error("Нет данных для отображения")
            } catch (e: Exception) {
                _videoState.value = VideoState.Error("Ошибка: ${e.message}")
            }
        }
    }
}
