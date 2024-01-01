package com.jiostb.myapplication.data.model.videos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegionRestriction(
    @SerialName("allowed")
    val allowed: List<String>?=null,
    @SerialName("blocked")
    val blocked: List<String>?=null
)