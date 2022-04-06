package com.rysanek.feeditemconsumer.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.domain.utils.FeedItemsDiffUtil
import com.rysanek.feeditemconsumer.presentation.viewholders.AdViewHolder
import com.rysanek.feeditemconsumer.presentation.viewholders.BigTopViewHolder
import com.rysanek.feeditemconsumer.presentation.viewholders.HouseAdViewHolder
import com.rysanek.feeditemconsumer.presentation.viewholders.RiverViewHolder
import com.rysanek.feeditemconsumer.presentation.viewholders.slideshow.SlideShowViewHolder

class FeedItemsRecyclerViewAdapter(
    var onItemClickListener: (FeedItemEntity) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val currentFeedItemsList: MutableList<FeedItemEntity> = mutableListOf()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return inflateViewHolderFromViewType(parent, viewType)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentFeedItem = currentFeedItemsList[position]
        
        when(holder){
            is BigTopViewHolder -> {
                holder.bind(currentFeedItem, onItemClickListener)
            }
            
            is RiverViewHolder -> {
                holder.bind(currentFeedItem, onItemClickListener)
            }
            
            is AdViewHolder -> {
                holder.bind(currentFeedItem, onItemClickListener)
            }
            
            is HouseAdViewHolder -> {
                holder.bind(currentFeedItem, onItemClickListener)
            }
            
            is SlideShowViewHolder ->{
                holder.setupCarouselView()
                holder.bind(currentFeedItem, onItemClickListener)
            }
        }
    }
    
    override fun getItemCount() = currentFeedItemsList.size
    
    fun setData(newFeedItems: List<FeedItemEntity>) {
        FeedItemsDiffUtil(currentFeedItemsList, newFeedItems).calculateDiff(this)
        currentFeedItemsList.clear()
        currentFeedItemsList.addAll(newFeedItems)
    }
    
    override fun getItemViewType(position: Int): Int {
        return itemTypeFromComponent(currentFeedItemsList[position].component)
    }
    
    private fun itemTypeFromComponent(component: Component) = when(component) {
        Component.BIG_TOP -> 0
        Component.RIVER -> 1
        Component.AD -> 2
        Component.HOUSE_AD -> 3
        Component.SLIDE_SHOW -> 4
    }
    
    private fun inflateViewHolderFromViewType(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> BigTopViewHolder.from(parent)
            1 -> RiverViewHolder.from(parent)
            2 -> AdViewHolder.from(parent)
            3 -> HouseAdViewHolder.from(parent)
            4 -> SlideShowViewHolder.from(parent)
            else -> BigTopViewHolder.from(parent)
            
        }
    }
}