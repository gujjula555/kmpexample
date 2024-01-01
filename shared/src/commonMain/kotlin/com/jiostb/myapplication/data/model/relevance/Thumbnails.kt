package com.jiostb.myapplication.data.model.relevance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    @SerialName("default")
    val default: Default,
    @SerialName("high")
    val high: High,
    @SerialName("medium")
    val medium: Medium
)