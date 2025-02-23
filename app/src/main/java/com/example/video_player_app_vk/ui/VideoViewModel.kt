package com.example.video_player_app_vk.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.video_player_app_vk.data.VideoRepository
import com.example.video_player_app_vk.data.model.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

