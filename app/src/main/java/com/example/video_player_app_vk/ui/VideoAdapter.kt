package com.example.video_player_app_vk.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.video_player_app_vk.data.model.VideoItem
import com.example.video_player_app_vk.databinding.ItemVideoBinding

class VideoAdapter(
    private val onItemClick: (VideoItem) -> Unit
) : ListAdapter<VideoItem, VideoAdapter.VideoViewHolder>(DiffCallback()) {

    inner class VideoViewHolder(private val binding: ItemVideoBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoItem) {
            binding.videoTitle.text = video.title
            binding.videoDuration.text = "${video.duration} сек"
            Glide.with(binding.videoThumbnail.context)
                .load(video.image)
                .into(binding.videoThumbnail)
            binding.root.setOnClickListener { onItemClick(video) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<VideoItem>() {
        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem) = oldItem == newItem
    }
}
