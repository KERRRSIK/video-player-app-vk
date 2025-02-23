package com.example.video_player_app_vk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.video_player_app_vk.data.local.VideoDao
import com.example.video_player_app_vk.data.model.VideoEntity

@Database(entities = [VideoEntity::class], version = 2, exportSchema = false)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

}

