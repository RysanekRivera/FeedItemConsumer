package com.rysanek.feeditemconsumer.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity

@Database(
    entities = [FeedItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FeedItemsDatabase: RoomDatabase() {
    
    abstract val feedItemsDao: FeedItemsDao
}