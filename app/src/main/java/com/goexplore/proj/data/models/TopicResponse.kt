package com.goexplore.proj.data.models

import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("Sound") val sound: List<TopicItem>,
    @SerializedName("Visuals") val visuals: List<TopicItem>,
    @SerializedName("Places") val places: List<TopicItem>
)

data class TopicItem(
    @SerializedName("label") val label: String,
    @SerializedName("emoji") val emoji: String? = null,
    @SerializedName("photo") val photo: String? = null
)
