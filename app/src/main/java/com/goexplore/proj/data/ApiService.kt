package com.goexplore.proj.data

import com.goexplore.proj.data.models.TopicResponse
import retrofit2.http.GET

interface ApiService {
    @GET("topics.json")
    suspend fun getTopics(): TopicResponse
}