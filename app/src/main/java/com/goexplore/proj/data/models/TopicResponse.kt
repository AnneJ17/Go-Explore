package com.goexplore.proj.data.models

data class TopicResponse(
    val sound: List<TopicItem>,
    val visuals: List<TopicItem>,
    val places: List<TopicItem>
)

data class TopicItem(
    val label: String,
    val emoji: String? = null,
    val photo: String? = null
)
