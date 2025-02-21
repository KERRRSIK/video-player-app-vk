package com.example.video_player_app_vk

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.video_player_app_vk.ui.VideoAdapter
import com.example.video_player_app_vk.ui.VideoViewModel
import com.example.video_player_app_vk.ui.theme.VideoplayerappvkTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var videoAdapter: VideoAdapter
    private val viewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        videoAdapter = VideoAdapter(emptyList()) { video ->
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("video_url", video.video_files.firstOrNull()?.link)
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = videoAdapter
        }

        // Подписка на обновления данных
        lifecycleScope.launch {
            viewModel.videoList.collect { videos ->
                videoAdapter = VideoAdapter(videos) { video ->
                    val intent = Intent(this@MainActivity, VideoPlayerActivity::class.java)
                    intent.putExtra("video_url", video.video_files.firstOrNull()?.link)
                    startActivity(intent)
                }
                recyclerView.adapter = videoAdapter
            }
        }

        // Жест для обновления
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadVideos("LoooqrfgKoxxDujubfLTSVKXKgMZq1ZNxBt46HGIkkhW6gNpQmzjGYg0")
            swipeRefreshLayout.isRefreshing = false
        }

        // Загружаем данные
        viewModel.loadVideos("LoooqrfgKoxxDujubfLTSVKXKgMZq1ZNxBt46HGIkkhW6gNpQmzjGYg0")
    }
}
