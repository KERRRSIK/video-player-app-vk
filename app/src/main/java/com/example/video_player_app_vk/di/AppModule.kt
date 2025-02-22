package com.example.video_player_app_vk.di

import android.content.Context
import androidx.room.Room
import com.example.video_player_app_vk.data.VideoDatabase
import com.example.video_player_app_vk.data.VideoRepository
import com.example.video_player_app_vk.data.local.VideoDao
import com.example.video_player_app_vk.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.pexels.com/v1/"  // Пример для Pexels API

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideVideoRepository(
        apiService: ApiService,
        videoDatabase: VideoDatabase
    ): VideoRepository = VideoRepository(apiService, videoDatabase.videoDao())

    @Provides
    @Singleton
    fun provideVideoDatabase(@ApplicationContext context: Context): VideoDatabase =
        Room.databaseBuilder(context, VideoDatabase::class.java, "video_db").build()

}
