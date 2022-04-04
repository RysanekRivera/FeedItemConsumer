package com.rysanek.feeditemconsumer.domain.usecases

import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.domain.repositories.FeedItemsRepositoryImpl
import javax.inject.Inject
/**
 * This Use Case Provides the functionality to cache data, refresh the cache and retrieve data
 * from the cache.
 * @param [repository] The repository with access to the database.
 * **/
class CacheData @Inject constructor(
    private val repository: FeedItemsRepositoryImpl
) {
    
    suspend fun cacheFeedItemsList(feedItemsList: List<FeedItemEntity>) = repository.cacheFeedItemsList(feedItemsList)
    
    suspend fun deleteCache() = repository.deleteCache()
    
    fun getAllFeedItemsList() = repository.getAllFeedItemsList()
    
    fun getListOfItemsSortedByComponent(component: Component) = repository.getListOfItemsSortedByComponent(component)
}