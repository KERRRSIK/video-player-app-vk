package com.example.video_player_app_vk.di

import android.content.Context
import androidx.room.Room
import com.example.video_player_app_vk.data.VideoDatabase
import com.example.video_player_app_vk.data.VideoRepository
import com.example.video_player_app_vk.data.local.VideoDao
import com.example.video_player_app_vk.data.network.ApiService
import com.example.video_player_app_vk.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VideoDatabase {
        return Room.databaseBuilder(
            context,
            VideoDatabase::class.java,
            "video_db"
        ).build()
    }

    @Provides
    fun provideVideoDao(database: VideoDatabase): VideoDao {
        return database.videoDao()
    }
}
