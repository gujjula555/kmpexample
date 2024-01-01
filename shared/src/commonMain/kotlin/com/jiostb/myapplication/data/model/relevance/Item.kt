package com.jiostb.myapplication.data.model.relevance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("etag")
    val etag: String,
    @SerialName("id")
    val id: Id,
    @SerialName("kind")
    val kind: String,
    @SerialName("snippet")
    val snippet: Snippet
)