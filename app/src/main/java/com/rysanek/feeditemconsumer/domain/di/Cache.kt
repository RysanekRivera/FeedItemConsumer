package com.rysanek.feeditemconsumer.domain.di

import android.app.Application
import androidx.room.Room
import com.rysanek.feeditemconsumer.data.local.db.FeedItemsDao
import com.rysanek.feeditemconsumer.data.local.db.FeedItemsDatabase
import com.rysanek.feeditemconsumer.domain.utils.Constants.FEED_ITEMS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Cache {
    
    @Provides
    @Singleton
    fun provideFeedItemsDatabase(
        application: Application
    ): FeedItemsDao = Room.databaseBuilder(
        application,
        FeedItemsDatabase::class.java,
        FEED_ITEMS_DATABASE_NAME
    ).build().feedItemsDao
}