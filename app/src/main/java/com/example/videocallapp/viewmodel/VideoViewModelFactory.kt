package com.example.videocallapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.videocallapp.repo.VideoRepo

class VideoViewModelFactory(private val videoRepo: VideoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(videoRepo) as T
    }

}