package com.jiostb.myapplication.data.model.videos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Localized(
    @SerialName("description")
    val description: String,
    @SerialName("title")
    val title: String
)