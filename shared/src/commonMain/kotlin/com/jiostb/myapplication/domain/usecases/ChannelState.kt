package com.jiostb.myapplication.domain.usecases

import com.jiostb.myapplication.data.model.channel.Channel
import com.jiostb.myapplication.data.model.videos.Youtube

sealed class ChannelState {
    object LOADING : ChannelState()
    data class SUCCESS(val channel: Channel) : ChannelState()
    data class ERROR(val error: String) : ChannelState()
}