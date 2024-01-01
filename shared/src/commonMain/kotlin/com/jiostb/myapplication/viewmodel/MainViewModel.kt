package com.jiostb.myapplication.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.jiostb.myapplication.data.model.videos.Item
import com.jiostb.myapplication.domain.repository.Repository
import com.jiostb.myapplication.domain.usecases.ChannelState
import com.jiostb.myapplication.domain.usecases.YoutubeState

class MainViewModel(private val repository: Repository) : ViewModel() {

    //Videos
    private val _videos = MutableStateFlow<YoutubeState>(YoutubeState.LOADING)
    val videos: StateFlow<YoutubeState> = _videos.asStateFlow()

    //Relevance
    private val _relevance = MutableStateFlow<YoutubeState>(YoutubeState.LOADING)
    val relevance: StateFlow<YoutubeState> = _relevance.asStateFlow()

    //Channel Details
    private val _channel = MutableStateFlow<ChannelState>(ChannelState.LOADING)
    val channelDetails : StateFlow<ChannelState> = _channel.asStateFlow()

    //Relevance Videos
    private val _relevance_videos = MutableStateFlow<YoutubeState>(YoutubeState.LOADING)
    val relevanceVideos : StateFlow<YoutubeState> = _relevance_videos.asStateFlow()

    fun getVideosList() {
        viewModelScope.launch {
            _videos.value = YoutubeState.LOADING
            try {
                val response = repository.getVideoList()
                _videos.value = YoutubeState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _videos.value = YoutubeState.ERROR(error)
            }

        }
    }

    fun getRelevance() {
        viewModelScope.launch {
            _relevance.value = YoutubeState.LOADING
            try {
                val response = repository.getRelevance()
                _relevance.value = YoutubeState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _relevance.value = YoutubeState.ERROR(error)
            }

        }
    }

    fun getChannelDetails(channelId: String) {
        viewModelScope.launch {
            _channel.value = ChannelState.LOADING
            try {
                val response = repository.getChannelDetail(channelId)
                _channel.value = ChannelState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _channel.value = ChannelState.ERROR(error)
            }

        }
    }

    fun getRelevanceVideos() {
        viewModelScope.launch {
            _relevance_videos.value = YoutubeState.LOADING
            try {
                val response = repository.getRelevanceVideos()
                _relevance_videos.value = YoutubeState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _relevance_videos.value = YoutubeState.ERROR(error)
            }

        }
    }

}