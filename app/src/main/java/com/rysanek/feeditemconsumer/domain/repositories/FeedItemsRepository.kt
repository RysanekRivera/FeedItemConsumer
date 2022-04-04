package com.rysanek.feeditemconsumer.domain.repositories

import androidx.lifecycle.LiveData
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.data.remote.models.FeedItem
import kotlinx.coroutines.flow.Flow

interface FeedItemsRepository {
    
    suspend fun fetchFeedItems(): Flow<List<FeedItem>>
    
    suspend fun cacheFeedItemsList(feedItemsList: List<FeedItemEntity>)
    
    suspend fun deleteCache()
    
    fun getAllFeedItemsList(): LiveData<List<FeedItemEntity>>
    
    fun getListOfItemsSortedByComponent(component: Component): LiveData<List<FeedItemEntity>>
}