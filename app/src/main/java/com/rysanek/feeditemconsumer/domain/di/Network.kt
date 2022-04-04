package com.rysanek.feeditemconsumer.domain.di

import com.rysanek.feeditemconsumer.data.remote.client.MockClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {
    /*Simulate passing an API object to the repository for network data fetching*/
    @Singleton
    @Provides
    fun provideMockClient(): MockClient = MockClient
}