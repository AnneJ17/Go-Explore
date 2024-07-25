package com.goexplore.proj.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goexplore.proj.data.TopicRepository
import com.goexplore.proj.data.models.TopicItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel
@Inject
constructor(
    private val repository: TopicRepository
) : ViewModel() {

    private val _topics = MutableStateFlow<List<Pair<String, List<TopicItem>>>>(emptyList())
    val topics: StateFlow<List<Pair<String, List<TopicItem>>>> = _topics

    // Manually iterate over the fields in TopicResponse to create a dynamic list of topics
    // We can use reflection to dynamically handle the fields, but that would be a overkill
    // TODO: Update the JSON file to return list, we can then use Map making the code maintainable
    init {
        viewModelScope.launch {
            val response = repository.getTopics()
            val dynamicTopics = mutableListOf<Pair<String, List<TopicItem>>>()

            if (response.sound.isNotEmpty()) {
                dynamicTopics.add("Sound" to response.sound)
            }
            if (response.visuals.isNotEmpty()) {
                dynamicTopics.add("Visuals" to response.visuals)
            }
            if (response.places.isNotEmpty()) {
                dynamicTopics.add("Places" to response.places)
            }

            _topics.value = dynamicTopics
        }
    }
}