package com.rysanek.feeditemconsumer.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.databinding.LayoutRiverBinding

class RiverViewHolder(private val binding: LayoutRiverBinding): RecyclerView.ViewHolder(binding.root){
    
    companion object {
        fun from(parent: ViewGroup): RiverViewHolder =  RiverViewHolder(
            LayoutRiverBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
    
    fun bind(feedItem: FeedItemEntity) {
        binding.textView.text = feedItem.headline
        Glide.with(binding.root.context)
            .load("https://github.com/RysanekRivera/FeedItemConsumer/blob/master/river.jpg?raw=true")
            .centerCrop()
            .into(binding.ivRiverBackground)
            
    }
}
