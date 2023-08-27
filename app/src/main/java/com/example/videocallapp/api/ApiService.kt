package com.example.videocallapp.api

import com.example.videocallapp.model.VideoDataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("VideoDummyApi/dummy_data.json")
    fun getItems(): Call<List<VideoDataModel>>

}