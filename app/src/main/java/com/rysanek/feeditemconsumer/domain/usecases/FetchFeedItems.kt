package com.rysanek.feeditemconsumer.domain.usecases

import com.rysanek.feeditemconsumer.domain.repositories.FeedItemsRepositoryImpl
import javax.inject.Inject

class FetchFeedItems @Inject constructor(
    private val repository: FeedItemsRepositoryImpl
) {
    
    suspend fun fetchItems() = repository.fetchFeedItems()
}