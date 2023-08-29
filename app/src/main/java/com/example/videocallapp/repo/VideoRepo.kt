package com.example.videocallapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.videocallapp.api.ApiService
import com.example.videocallapp.model.VideoDataModel

class VideoRepo(private val apiinterface: ApiService) {

    private val videoLiveData = MutableLiveData<List<VideoDataModel>>()
    val video : LiveData<List<VideoDataModel>>
        get() = videoLiveData

    suspend fun getMemes(){
        val result = apiinterface.getItems()
        if (result!=null){
            videoLiveData.postValue(result.execute().body())
        }
    }
}