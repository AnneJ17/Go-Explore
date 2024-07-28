package com.goexplore.proj.ux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goexplore.proj.R
import com.goexplore.proj.data.models.TopicItem

class GridItemAdapter(
    private val items: List<TopicItem>,
    private val onItemClick: (TopicItem) -> Unit
) : RecyclerView.Adapter<GridItemAdapter.GridItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic_item, parent, false)
        return GridItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = items.size

    inner class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemEmoji: TextView = itemView.findViewById(R.id.itemEmoji)
        private val itemText: TextView = itemView.findViewById(R.id.itemText)

        fun bind(item: TopicItem) {
            itemText.text = item.label
            item.emoji?.let {
                itemEmoji.text = it
            }
        }
    }
}
