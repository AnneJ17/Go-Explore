package com.goexplore.proj.ux

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goexplore.proj.R
import com.goexplore.proj.viewmodels.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopicsActivity : AppCompatActivity() {

    private val viewModel: TopicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        val topicContainer: LinearLayout = findViewById(R.id.topicContainer)
        val nextButton: Button = findViewById(R.id.nextButton)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topics.collect { topics ->
                    topicContainer.removeAllViews() // Clear any existing views
                    topics.forEach { (topicType, items) ->
                        val topicView =
                            layoutInflater.inflate(R.layout.item_topic, topicContainer, false)
                        val topicLabel: TextView = topicView.findViewById(R.id.topicLabel)
                        val itemRecyclerView: RecyclerView =
                            topicView.findViewById(R.id.itemRecyclerView)

                        topicLabel.text = topicType

                        if (topicType == "Visuals") {
                            itemRecyclerView.layoutManager = LinearLayoutManager(
                                this@TopicsActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            val visualAdapter = VisualsAdapter()
                            itemRecyclerView.adapter = visualAdapter
                            visualAdapter.submitList(items)
                        } else {
                            itemRecyclerView.layoutManager =
                                GridLayoutManager(this@TopicsActivity, 3)
                            itemRecyclerView.adapter = GridItemAdapter(items) { item ->
                                // Handle item click
                            }
                        }

                        topicContainer.addView(topicView)
                    }
                }
            }

            nextButton.setOnClickListener {
                // Handle next button click
            }
        }
    }
}