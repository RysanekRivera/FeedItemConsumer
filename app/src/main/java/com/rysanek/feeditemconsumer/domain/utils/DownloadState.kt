package com.rysanek.feeditemconsumer.domain.utils

sealed class DownloadState{
    object Idle : DownloadState()
    object Checking: DownloadState()
    object Downloading: DownloadState()
    object Finished: DownloadState()
    data class Error(val message: String): DownloadState()
    
}
