package com.jiostb.myapplication.data.model.relevance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    @SerialName("resultsPerPage")
    val resultsPerPage: Int,
    @SerialName("totalResults")
    val totalResults: Int
)