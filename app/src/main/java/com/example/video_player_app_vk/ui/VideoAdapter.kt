package com.example.video_player_app_vk.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.video_player_app_vk.R
import com.example.video_player_app_vk.data.model.VideoItem

class VideoAdapter(private val videos: List<VideoItem>, private val onClick: (VideoItem) -> Unit) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.videoThumbnail)
        private val title: TextView = itemView.findViewById(R.id.videoTitle)
        private val duration: TextView = itemView.findViewById(R.id.videoDuration)

        fun bind(video: VideoItem, onClick: (VideoItem) -> Unit) {
            title.text = video.url // API не дает заголовок, берем URL
            duration.text = "${video.duration} сек"
            Glide.with(itemView.context).load(video.image).into(thumbnail)

            itemView.setOnClickListener { onClick(video) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position], onClick)
    }

    override fun getItemCount(): Int = videos.size
}
