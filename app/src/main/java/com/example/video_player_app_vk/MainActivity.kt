package com.example.video_player_app_vk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.video_player_app_vk.databinding.ActivityMainBinding
import com.example.video_player_app_vk.ui.VideoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, VideoListFragment())
            .commit()
    }
}
