package com.example.video_player_app_vk.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.video_player_app_vk.data.model.VideoEntity
import com.example.video_player_app_vk.databinding.ItemVideoBinding

class VideoAdapter(
    private val onItemClick: (VideoEntity) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var videos: List<VideoEntity> = emptyList()

    fun submitList(newVideos: List<VideoEntity>) {
        videos = newVideos
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoEntity) {
            binding.videoTitle.text = video.title
            binding.videoDuration.text = "${video.duration} сек"
            Glide.with(binding.videoThumbnail.context)
                .load(video.thumbnailUrl)
                .into(binding.videoThumbnail)

            binding.root.setOnClickListener { onItemClick(video) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size
}
