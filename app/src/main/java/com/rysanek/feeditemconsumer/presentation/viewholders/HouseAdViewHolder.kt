package com.rysanek.feeditemconsumer.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.databinding.LayoutHouseAdBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HouseAdViewHolder(private val binding: LayoutHouseAdBinding): RecyclerView.ViewHolder(binding.root) {
    private var adRequest: AdRequest? = null
    
    companion object {
        fun from(parent: ViewGroup): HouseAdViewHolder =  HouseAdViewHolder(
            LayoutHouseAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    
    fun bind(feedItem: FeedItemEntity) {
        
        binding.textView5.text = feedItem.headline
    
        if (adRequest != null) {
            binding.houseAdView.loadAd(adRequest!!)
        } else {
            CoroutineScope(Dispatchers.IO + Job()).launch {
                adRequest = AdRequest.Builder().build()
                
                withContext(Dispatchers.Main + Job()){
                    binding.houseAdView.loadAd(adRequest!!)
                }
            }
        }
    }
}