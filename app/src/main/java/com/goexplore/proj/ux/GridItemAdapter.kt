package com.goexplore.proj.ux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.itemEmoji)
        private val text: TextView = itemView.findViewById(R.id.itemText)

        fun bind(item: TopicItem) {
            text.text = item.label
            if (item.photo != null) {
                Glide.with(itemView.context).load(item.photo).into(image)
            } else {
//                image.setImageResource(R.drawable.default_image) // Or some placeholder
            }
        }
    }
}
