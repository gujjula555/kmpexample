package com.jiostb.myapplication.domain.usecases

import com.jiostb.myapplication.data.model.videos.Youtube

sealed class YoutubeState {
    object LOADING : YoutubeState()
    data class SUCCESS(val youtube: Youtube) : YoutubeState()
    data class ERROR(val error: String) : YoutubeState()
}