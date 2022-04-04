package com.rysanek.feeditemconsumer.domain.repositories

import com.rysanek.feeditemconsumer.data.local.db.FeedItemsDao
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.FeedItem
import com.rysanek.feeditemconsumer.data.remote.client.MockClient
import com.rysanek.feeditemconsumer.data.remote.models.Component
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeedItemsRepositoryImpl @Inject constructor(
    private val mockClient: MockClient,
    private val cache: FeedItemsDao
): FeedItemsRepository {
    
    override suspend fun fetchFeedItems(): Flow<List<FeedItem>> = flow { emit(mockClient.getFeed()) }
    
    override suspend fun cacheFeedItemsList(feedItemsList: List<FeedItemEntity>) = cache.cacheFeedItemsList(feedItemsList)
    
    override suspend fun deleteCache() = cache.deleteCache()
    
    override fun getAllFeedItemsList() = cache.getAllFeedItemsList()
    
    override fun getListOfItemsSortedByComponent(component: Component) = cache.getListOfItemsSortedByComponent(component)
}