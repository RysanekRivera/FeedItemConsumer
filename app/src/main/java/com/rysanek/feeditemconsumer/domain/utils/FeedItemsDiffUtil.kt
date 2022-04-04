package com.rysanek.feeditemconsumer.domain.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity

class FeedItemsDiffUtil(private val oldList: List<FeedItemEntity>, private val newList: List<FeedItemEntity>): DiffUtil.Callback() {
    
    override fun getOldListSize() = oldList.size
    
    override fun getNewListSize() = newList.size
    
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
    
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList [newItemPosition]
    }
    
    /**
     * Handles calculating the difference between two lists and updating the UI accordingly.
     * @param adapter The [RecyclerView.Adapter] to which the changes will dispatch to.
     */
    fun <T: RecyclerView.ViewHolder> calculateDiff(adapter: RecyclerView.Adapter<T>) =
        DiffUtil.calculateDiff(this).dispatchUpdatesTo(adapter)
}