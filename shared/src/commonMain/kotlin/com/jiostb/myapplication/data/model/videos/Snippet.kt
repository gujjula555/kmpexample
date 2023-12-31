package com.jiostb.myapplication.data.model.videos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet(
    @SerialName("categoryId")
    val categoryId: String,
    @SerialName("channelId")
    val channelId: String,
    @SerialName("channelTitle")
    val channelTitle: String,
    @SerialName("defaultAudioLanguage")
    val defaultAudioLanguage: String?,
    @SerialName("defaultLanguage")
    val defaultLanguage: String?="",
    @SerialName("description")
    val description: String,
    @SerialName("liveBroadcastContent")
    val liveBroadcastContent: String,
    @SerialName("localized")
    val localized: Localized,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("tags")
    val tags: List<String>?=null,
    @SerialName("thumbnails")
    val thumbnails: Thumbnails,
    @SerialName("title")
    val title: String
)
