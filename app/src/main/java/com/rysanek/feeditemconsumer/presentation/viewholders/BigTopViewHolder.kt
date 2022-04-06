package com.rysanek.feeditemconsumer.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.databinding.LayoutBigTopBinding

class BigTopViewHolder(private val binding: LayoutBigTopBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): BigTopViewHolder = BigTopViewHolder(LayoutBigTopBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    
    fun bind(feedItem: FeedItemEntity, onItemClickListener: (FeedItemEntity) -> Unit) {
        binding.textView2.text = feedItem.headline
        binding.root.setOnClickListener { onItemClickListener(feedItem) }
    }
}