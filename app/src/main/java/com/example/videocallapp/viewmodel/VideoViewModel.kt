package com.example.videocallapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videocallapp.model.VideoDataModel
import com.example.videocallapp.repo.VideoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(private val videoRepo: VideoRepo) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            videoRepo.getMemes()
        }
    }

    val video: LiveData<List<VideoDataModel>>
        get() = videoRepo.video

}