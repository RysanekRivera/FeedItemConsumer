package com.rysanek.feeditemconsumer.presentation.viewmodels

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rysanek.feeditemconsumer.R
import com.rysanek.feeditemconsumer.data.local.entities.FeedItemEntity
import com.rysanek.feeditemconsumer.data.remote.models.Component
import com.rysanek.feeditemconsumer.data.remote.models.FeedItem
import com.rysanek.feeditemconsumer.domain.mappers.toFeedItemEntityList
import com.rysanek.feeditemconsumer.domain.usecases.CacheData
import com.rysanek.feeditemconsumer.domain.usecases.FetchFeedItems
import com.rysanek.feeditemconsumer.domain.utils.DownloadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedItemsViewModel @Inject constructor(
    private val fetchFeedItems: FetchFeedItems,
    private val cacheData: CacheData
): ViewModel() {
    
    private val _downloadState: MutableStateFlow<DownloadState> = MutableStateFlow(DownloadState.Idle)
    val downloadState: StateFlow<DownloadState> get() = _downloadState
    
    /*
     monitors different LiveData depending on the filter selected by the user.
     The default is to getAllFeedItemsList from the cache
     Returns the correct LiveData either all or sorted by Component, which then passes the list to
     the adapter.
    */
    private var _feedItemsList = MutableStateFlow(cacheData.getAllFeedItemsList())
    val feedItemsList get() = _feedItemsList
    
    // Launches the request to fetch data
    fun launchFeedItemsFetch() = viewModelScope.launch(Dispatchers.IO) { fetchData() }
    
    // Resets the download state to idle
    fun resetDownloadState() = viewModelScope.launch{ _downloadState.emit(DownloadState.Idle) }
    
    private suspend fun fetchData() {
        _downloadState.emit(DownloadState.Downloading)
        
        cacheData.deleteCache()
        
        // Simulate Long Running Operation
         delay(2000)
        
        fetchFeedItems.fetchItems()
            .catch { e -> _downloadState.emit(DownloadState.Error(e.message ?: "An Unknown Error Occurred")) }
            .onCompletion { _downloadState.emit(DownloadState.Finished) }
            .map { feedItemList -> feedItemList.toFeedItemEntityList() }
            .collect{ feedItemsEntityList -> cacheData.cacheFeedItemsList(feedItemsEntityList) }
    }
    
    fun setSortedList(menuItem: MenuItem): Boolean {
        
        when(menuItem.itemId) {
            R.id.miNoFilter -> viewModelScope.launch { _feedItemsList.emit(cacheData.getAllFeedItemsList()) }
            R.id.miBigTop -> viewModelScope.launch { _feedItemsList.emit(cacheData.getListOfItemsSortedByComponent(Component.BIG_TOP)) }
            R.id.miRiver -> viewModelScope.launch { _feedItemsList.tryEmit(cacheData.getListOfItemsSortedByComponent(Component.RIVER)) }
            R.id.miAd -> viewModelScope.launch { _feedItemsList.tryEmit(cacheData.getListOfItemsSortedByComponent(Component.AD)) }
            R.id.miHouseAd -> viewModelScope.launch { _feedItemsList.emit(cacheData.getListOfItemsSortedByComponent(Component.HOUSE_AD)) }
            R.id.miSlideShow -> viewModelScope.launch { _feedItemsList.emit(cacheData.getListOfItemsSortedByComponent(Component.SLIDE_SHOW)) }
            else -> _feedItemsList.tryEmit(cacheData.getAllFeedItemsList()) }
        
        return true
    }
    
    
}