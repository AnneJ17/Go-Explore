package com.goexplore.proj.ux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goexplore.proj.R
import com.goexplore.proj.data.models.TopicItem

class VisualsAdapter(
    private val items: List<TopicItem>
) : RecyclerView.Adapter<VisualsAdapter.VisualsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisualsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_visuals, parent, false)
        return VisualsViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisualsViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(item.photo).into(holder.photo)
    }

    override fun getItemCount() = items.size

    class VisualsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.itemPhoto)
    }
}