package com.example.videocallapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videocallapp.model.VideoDataModel
import com.example.videocallapp.repo.MemesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesViewModel(private val memesRepo: MemesRepo) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            memesRepo.getMemes()
        }
    }

    val meme: LiveData<List<VideoDataModel>>
        get() = memesRepo.memes

}