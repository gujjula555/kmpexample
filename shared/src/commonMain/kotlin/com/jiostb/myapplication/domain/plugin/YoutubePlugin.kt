package com.jiostb.myapplication.domain.plugin

import com.jiostb.myapplication.data.model.channel.Channel
import com.jiostb.myapplication.data.model.videos.Item
import com.jiostb.myapplication.data.model.videos.Youtube

interface YoutubePlugin {
    suspend fun getVideoList() : Youtube
    suspend fun getRelevance(): Youtube
    suspend fun getChannelDetail(channelId: String): Channel
    suspend fun getRelevanceVideos(): Youtube
}