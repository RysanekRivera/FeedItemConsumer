package com.rysanek.feeditemconsumer.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.domain.utils.Constants.FEED_ITEMS_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedItemsDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheFeedItemsList(feedItemsList: List<FeedItemEntity>)
    
    @Query("DELETE FROM $FEED_ITEMS_TABLE_NAME")
    suspend fun deleteCache()
    
    @Query("SELECT * FROM $FEED_ITEMS_TABLE_NAME")
    fun getAllFeedItemsList(): LiveData<List<FeedItemEntity>>
    
    @Query("SELECT * FROM $FEED_ITEMS_TABLE_NAME WHERE component = :component")
    fun getListOfItemsSortedByComponent(component: Component): LiveData<List<FeedItemEntity>>
}