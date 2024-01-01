package com.jiostb.myapplication.data.model.videos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Standard(
    @SerialName("height")
    val height: Int,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)