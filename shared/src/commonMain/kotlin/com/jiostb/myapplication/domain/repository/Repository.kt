package com.jiostb.myapplication.domain.repository

import co.touchlab.kermit.Logger
import com.jiostb.myapplication.data.model.channel.Channel
import com.jiostb.myapplication.data.model.videos.Item
import com.jiostb.myapplication.data.model.videos.Youtube
import com.jiostb.myapplication.domain.plugin.YoutubePlugin
import com.jiostb.myapplication.utils.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.company.app.data.remote.YoutubeClientApi

class Repository(private val httpClient: HttpClient) : YoutubePlugin {

    override suspend fun getVideoList(): Youtube {
        val url = Constant.BASE_URL + "videos?part=contentDetails%2Csnippet%2Cstatistics&chart=mostPopular&regionCode=us&maxResults=2000&key=${Constant.API_KEY}"
        val response = httpClient.get(url).body<Youtube>()
        return response
    }

    /*override suspend fun getVideoList(): Youtube {
       return YoutubeClientApi.getVideoList()
    }*/

    override suspend fun getRelevance(): Youtube {
        return YoutubeClientApi.getRelevance()
    }

    override suspend fun getChannelDetail(channelId: String): Channel {
        return YoutubeClientApi.getChannelDetails(channelId)
    }

    override suspend fun getRelevanceVideos(): Youtube {
        return YoutubeClientApi.getRelevanceVideos()
    }
}