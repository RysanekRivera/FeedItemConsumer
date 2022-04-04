package com.rysanek.feeditemconsumer.domain.mappers

import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.FeedItem

/**
 * Transforms a single [FeedItem] object into a database entity object of type [FeedItemEntity]
 * **/
fun FeedItem.toFeedItemEntityList(): FeedItemEntity = FeedItemEntity(component, headline, imageUrl)

/**
 * Transforms a list of [FeedItem] objects into database entity objects of type [FeedItemEntity]
 * **/
fun List<FeedItem>.toFeedItemEntityList(): List<FeedItemEntity> {
    val entitiesList = mutableListOf<FeedItemEntity>()
    forEach { feedItem -> entitiesList.add(feedItem.toFeedItemEntityList()) }
    return entitiesList
}