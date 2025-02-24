package com.example.video_player_app_vk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _videoState = MutableLiveData<VideoState>()
    val videoState: LiveData<VideoState> get() = _videoState

    fun loadTopClips(authToken: String, clientId: String) {
        viewModelScope.launch {
            _videoState.value = VideoState.Loading
            val videos = repository.getTopClips(authToken, clientId)
            if (videos.isNotEmpty()) {
                _videoState.value = VideoState.Success(videos)
            } else {
                _videoState.value = VideoState.Error("Ошибка загрузки клипов")
            }
        }
    }
}
