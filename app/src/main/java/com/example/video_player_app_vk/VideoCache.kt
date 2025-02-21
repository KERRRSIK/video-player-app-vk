package com.example.video_player_app_vk

import android.content.Context
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

object VideoCache {
    fun getCache(context: Context): SimpleCache {
        val cacheDir = File(context.cacheDir, "video_cache")
        val evictor = LeastRecentlyUsedCacheEvictor(100 * 1024 * 1024) // 100MB
        return SimpleCache(cacheDir, evictor, StandaloneDatabaseProvider(context))
    }
}
