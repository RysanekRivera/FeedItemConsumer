package com.rysanek.feeditemconsumer.domain.utils

/**
 * This Sealed class handles the state during a fetch request.
 * @property [Idle] Handles when no request is being performed. Makes sure UI signaling a
 * download are turned off.
 * @property [Checking] Checking if there is internet connection and if not handling how to tell
 * the user.
 * @property [Downloading] Active phase of the download. Here you handle all UI changes letting
 * the user now a download is in progress.
 * @property [Error] Handles letting the user now when there is an error.
 * @property [Finished] Whether there is an error or not this returns the download state to [Idle]
 * **/
sealed class DownloadState{
    object Idle : DownloadState()
    object Checking: DownloadState()
    object Downloading: DownloadState()
    object Finished: DownloadState()
    data class Error(val message: String): DownloadState()
    
}
