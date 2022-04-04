package com.rysanek.feeditemconsumer.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import com.rysanek.feeditemconsumer.R
import com.rysanek.feeditemconsumer.databinding.ActivityMainBinding
import com.rysanek.feeditemconsumer.domain.utils.DownloadState
import com.rysanek.feeditemconsumer.domain.utils.fullScreenMode
import com.rysanek.feeditemconsumer.domain.utils.gone
import com.rysanek.feeditemconsumer.domain.utils.hasInternetConnection
import com.rysanek.feeditemconsumer.domain.utils.show
import com.rysanek.feeditemconsumer.presentation.adapters.FeedItemsRecyclerViewAdapter
import com.rysanek.feeditemconsumer.presentation.viewmodels.FeedItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    
    private lateinit var feedItemsAdapter: FeedItemsRecyclerViewAdapter
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var popupMenu: PopupMenu? = null
    private val viewModel: FeedItemsViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        MobileAds.initialize(this)
        
        fullScreenMode()
        
        super.onCreate(savedInstanceState)
        
        _binding = ActivityMainBinding.inflate(layoutInflater)
        
        fetchFeedItemsData()
        
        setupRecyclerView()
        
        observeDownloadState()
        
        observeFeedItemsList()
        
        setContentView(binding.root)
        
        setupOptionsMenu()
        
    }
    
    private fun setupRecyclerView() {
        
        feedItemsAdapter = FeedItemsRecyclerViewAdapter()
        
        with(binding.rvFeedItems) {
            
            adapter = feedItemsAdapter.apply {
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
            
            layoutManager = LinearLayoutManager(this@MainActivity)
            
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
        }
    }
    
    private fun setupOptionsMenu() {
        
        binding.ivPopUpMenu.setOnClickListener {
            popupMenu = PopupMenu(this, binding.ivPopUpMenu)
            
            popupMenu!!.menuInflater.inflate(R.menu.filter_menu, popupMenu!!.menu)
            
            popupMenu!!.apply {
                
                setForceShowIcon(true)
                
                setOnMenuItemClickListener { menuItem ->
                    viewModel.setSortedList(menuItem)
                }
                
                setOnDismissListener { popupMenu = null }
            }
            
            popupMenu!!.show()
        }
    }
    
    private fun fetchFeedItemsData() {
        if (hasInternetConnection()) {
            viewModel.launchFeedItemsFetch()
        } else {
            Snackbar.make(
                binding.root,
                "No Internet Connection. Data might not be updated.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
    
    private fun observeDownloadState() {
        viewModel.downloadState.asLiveData().observe(this) { state ->
            handleDownloadState(state)
        }
    }
    
    private fun observeFeedItemsList() {
        viewModel.feedItemsList.asLiveData().observe(this) { liveData ->
            liveData.observe(this) { feedItemsList ->
                Log.d("Main", "${feedItemsList?.toString()}")
                feedItemsAdapter.setData(feedItemsList ?: emptyList())
            }
        }
    }
    
    private fun handleDownloadState(state: DownloadState) {
        when (state) {
            DownloadState.Idle -> {
                binding.progressBar.gone()
            }
            DownloadState.Downloading -> {
                binding.progressBar.show()
            }
            DownloadState.Finished -> {
                binding.progressBar.gone()
                viewModel.resetDownloadState()
            }
            is DownloadState.Error -> {
                binding.progressBar.gone()
                Snackbar.make(binding.root, state.message, Snackbar.LENGTH_SHORT).show()
            }
            else -> { /* NO-OP */
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
}