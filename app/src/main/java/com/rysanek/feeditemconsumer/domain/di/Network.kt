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
    
    @Singleton
    @Provides
    fun provideMockClient(): MockClient = MockClient
}