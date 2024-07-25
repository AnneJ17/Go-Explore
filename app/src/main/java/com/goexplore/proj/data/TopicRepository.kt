package com.goexplore.proj.data

import com.goexplore.proj.data.models.TopicResponse
import javax.inject.Inject

class TopicRepository
@Inject
constructor(
    private val apiService: ApiService
) {
    suspend fun getTopics(): TopicResponse {
        return apiService.getTopics()
    }
}