package com.rysanek.feeditemconsumer.domain.mappers

import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.FeedItem

fun FeedItem.toFeedItemEntityList(): FeedItemEntity = FeedItemEntity(component, headline, imageUrl)

fun List<FeedItem>.toFeedItemEntityList(): List<FeedItemEntity> {
    val entitiesList = mutableListOf<FeedItemEntity>()
    forEach { feedItem -> entitiesList.add(feedItem.toFeedItemEntityList()) }
    return entitiesList
}