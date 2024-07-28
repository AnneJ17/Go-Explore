package com.goexplore.proj.ux

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goexplore.proj.R
import com.goexplore.proj.data.models.TopicItem

class VisualsAdapter : ListAdapter<TopicItem, VisualsAdapter.VisualsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisualsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_visuals, parent, false)
        return VisualsViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisualsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class VisualsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.itemPhoto)

        fun bind(item: TopicItem) {
            item.photo?.let {
                Log.d("VisualsAdapter", "Loading image: $it")
                Glide.with(itemView.context)
                    .load(it)
//                    .placeholder(R.drawable.placeholder) // Optional placeholder
                    .error(R.drawable.ic_launcher_foreground) // Fallback image
                    .into(photo)
            } ?: run {
                photo.setImageResource(R.drawable.ic_launcher_foreground) // Set a default image or placeholder
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TopicItem>() {
        override fun areItemsTheSame(oldItem: TopicItem, newItem: TopicItem): Boolean {
            return oldItem.label == newItem.label
        }

        override fun areContentsTheSame(oldItem: TopicItem, newItem: TopicItem): Boolean {
            return oldItem == newItem
        }
    }
}