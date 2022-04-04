package com.rysanek.feeditemconsumer.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.databinding.LayoutAdBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdViewHolder(private val binding: LayoutAdBinding): RecyclerView.ViewHolder(binding.root) {
    private var adRequest: AdRequest? = null
    
    companion object {
        fun from(parent: ViewGroup): AdViewHolder {
            val view =
                LayoutAdBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            
            return AdViewHolder(view)
        }
    }
    
    fun bind(feedItem: FeedItemEntity) {
        
        binding.textView4.text = feedItem.headline
        
        if (adRequest != null) {
            binding.adView.loadAd(adRequest!!)
        } else {
            CoroutineScope(Dispatchers.IO + Job()).launch {
                adRequest = AdRequest.Builder().build()
                
                withContext(Dispatchers.Main + Job()){
                    binding.adView.loadAd(adRequest!!)
                }
            }
        }
        
    }
}