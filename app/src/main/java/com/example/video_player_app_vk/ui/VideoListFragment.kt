package com.example.video_player_app_vk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.video_player_app_vk.databinding.FragmentVideoListBinding
import dagger.hilt.android.AndroidEntryPoint

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
        adapter = VideoAdapter { videoItem ->
            // При клике открываем VideoPlayerActivity
            val intent = VideoPlayerActivity.newIntent(requireContext(), videoItem.video_files.firstOrNull()?.link ?: "")
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadVideos()
        }

        viewModel.videoState.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state is VideoState.Loading
            binding.recyclerView.isVisible = state is VideoState.Success
            binding.errorTextView.isVisible = state is VideoState.Error

            when(state) {
                is VideoState.Success -> adapter.submitList(state.videos)
                is VideoState.Error -> binding.errorTextView.text = state.message
                else -> Unit
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.loadVideos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
