package com.example.videocallapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.videocallapp.api.ApiService
import com.example.videocallapp.model.VideoDataModel
import retrofit2.await

class MemesRepo(private val apiinterface: ApiService) {

    private val memesLiveData = MutableLiveData<List<VideoDataModel>>()
    val memes : LiveData<List<VideoDataModel>>
        get() = memesLiveData

    suspend fun getMemes(){
        val result = apiinterface.getItems()
        if (result!=null){
            memesLiveData.postValue(result.execute().body())
        }
    }
}