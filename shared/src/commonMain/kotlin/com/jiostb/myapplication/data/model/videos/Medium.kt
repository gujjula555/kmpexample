package com.jiostb.myapplication.data.model.videos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Medium(
    @SerialName("height")
    val height: Int=0,
    @SerialName("url")
    val url: String?=null,
    @SerialName("width")
    val width: Int=0
)