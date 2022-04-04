package com.rysanek.feeditemconsumer.domain.usecases

import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.domain.repositories.FeedItemsRepositoryImpl
import javax.inject.Inject

class CacheData @Inject constructor(
    private val repository: FeedItemsRepositoryImpl
) {
    
    suspend fun cacheFeedItemsList(feedItemsList: List<FeedItemEntity>) = repository.cacheFeedItemsList(feedItemsList)
    
    suspend fun deleteCache() = repository.deleteCache()
    
    fun getAllFeedItemsList() = repository.getAllFeedItemsList()
    
    fun getListOfItemsSortedByComponent(component: Component) = repository.getListOfItemsSortedByComponent(component)
}