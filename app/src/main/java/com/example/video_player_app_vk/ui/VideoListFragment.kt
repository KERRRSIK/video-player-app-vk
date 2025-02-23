package com.example.video_player_app_vk.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.video_player_app_vk.databinding.FragmentVideoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoListFragment : Fragment() {

    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VideoListViewModel by viewModels()
    private lateinit var adapter: VideoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d("VideoListFragment", "onCreateView called")
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = VideoAdapter { video ->
            // Открываем VideoPlayerActivity, передавая ссылку на видео
            val intent = VideoPlayerActivity.newIntent(requireContext(), video.videoUrl)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadVideos()
        }

        // Подписка на Flow (StateFlow)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.videoState.collectLatest { videos ->
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = videos.isNotEmpty()
                binding.errorTextView.isVisible = videos.isEmpty()

                if (videos.isEmpty()) {
                    binding.errorTextView.text = "Нет доступных видео"
                } else {
                    adapter.submitList(videos)
                }

                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        viewModel.loadVideos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
