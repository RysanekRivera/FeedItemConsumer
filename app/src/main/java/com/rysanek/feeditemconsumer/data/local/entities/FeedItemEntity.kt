package com.rysanek.feeditemconsumer.data.local.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.domain.utils.Constants.FEED_ITEMS_TABLE_NAME

@Entity(tableName = FEED_ITEMS_TABLE_NAME)
data class FeedItemEntity(val component: Component, val headline: String, val imageUrl: String?){
    @PrimaryKey var primaryKey: Int? = null
}
