package com.rysanek.feeditemconsumer.presentation.viewholders.slideshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.feeditemconsumer.R
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.databinding.LayoutSlideShowBinding
import com.rysanek.feeditemconsumer.presentation.adapters.SlideShowAdapter

class SlideShowViewHolder(private val binding: LayoutSlideShowBinding): RecyclerView.ViewHolder(binding.root) {
    private var slideShowAdapter: SlideShowAdapter? = null
    
    companion object {
        fun from(parent: ViewGroup): SlideShowViewHolder =  SlideShowViewHolder(
            LayoutSlideShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        
    }
    
    fun bind(feedItem: FeedItemEntity) {
        binding.textView6.text = feedItem.headline
    }
    
    fun setupCarouselView() {
        
        val items = listOf<ItemModel>(
            ItemModel("The Dawn of Time", "Dummy Data, Dummy Data, Dummy Data", R.drawable.dawn_of_time),
            ItemModel("Highlander", "He man, He man, He man", R.drawable.highlander),
            ItemModel("Batman", "The dark night with the joker.", R.drawable.batman),
            ItemModel("Superman", "The man of steel ", R.drawable.superman),
            ItemModel("Aquaman", "The ruler of the seas", R.drawable.aquaman)
        )
        
        slideShowAdapter =  SlideShowAdapter()
        
        binding.rvCarouselView.apply {
            
            adapter = slideShowAdapter!!.apply {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
            
            slideShowAdapter!!.setData(items)
            
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            
        }
    }
}