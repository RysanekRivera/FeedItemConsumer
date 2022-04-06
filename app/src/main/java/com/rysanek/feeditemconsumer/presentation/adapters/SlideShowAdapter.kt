package com.rysanek.feeditemconsumer.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.feeditemconsumer.databinding.SingleCarouselItemBinding
import com.rysanek.feeditemconsumer.presentation.viewholders.slideshow.ItemModel

class SlideShowAdapter(
    var onItemClickListener: (ItemModel) -> Unit
): RecyclerView.Adapter<SlideShowAdapter.SlideShowViewHolder>() {
    private val itemsList = mutableListOf<ItemModel>()
    
    class SlideShowViewHolder(private val binding: SingleCarouselItemBinding): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): SlideShowViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val singleLayout = SingleCarouselItemBinding.inflate(inflater, parent, false)
                return SlideShowViewHolder(singleLayout)
            }
        }
        
        fun bind(itemModel: ItemModel, onItemClickListener: (ItemModel) -> Unit){
            binding.imageView.setImageDrawable(ContextCompat.getDrawable(binding.root.context, itemModel.image))
            binding.root.setOnClickListener { onItemClickListener(itemModel) }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideShowViewHolder {
        return SlideShowViewHolder.from(parent)
    }
    
    override fun onBindViewHolder(holder: SlideShowViewHolder, position: Int) {
        val currentItem = itemsList[position]
        holder.bind(currentItem, onItemClickListener)
    }
    
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ItemModel>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }
    
    override fun getItemCount() = itemsList.size
    
}