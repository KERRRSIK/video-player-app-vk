package com.example.video_player_app_vk.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.video_player_app_vk.databinding.FragmentVideoListBinding
import com.example.video_player_app_vk.data.model.VideoEntity
import dagger.hilt.android.AndroidEntryPoint

sealed class VideoState {
    object Loading : VideoState()
    data class Success(val videos: List<VideoEntity>) : VideoState()
    data class Error(val message: String) : VideoState()
}

@AndroidEntryPoint
class VideoListFragment : Fragment() {

    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VideoListViewModel by viewModels()
    private lateinit var adapter: VideoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = VideoAdapter { video ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.videoUrl))
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadClips()
        }

        viewModel.videoState.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state is VideoState.Loading
            binding.recyclerView.isVisible = state is VideoState.Success
            binding.errorTextView.isVisible = state is VideoState.Error

            when (state) {
                is VideoState.Success -> adapter.submitList(state.videos)
                is VideoState.Error -> binding.errorTextView.text = state.message
                else -> {}
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        loadClips()
    }

    private fun loadClips() {
        val authToken = "k8qzkxcq98ub5pfvrxw0beztqlmu93"
        val clientId = "5lw0x8clzh3157dzstskkb3e7r6jeb"
        viewModel.loadTopClips(authToken, clientId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
