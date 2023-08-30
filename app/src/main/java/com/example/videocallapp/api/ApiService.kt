package com.example.videocallapp.api

import com.example.videocallapp.model.VideoDataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("VideoDummyApi/dummy_data.json")
    fun getItems(): Call<List<VideoDataModel>>
    

    //https://gist.githubusercontent.com/sumit2607/acbb529d09309e181b4c38ba703c1547/raw/4b6e93d6c1048549514bf24ff442d4cccb9074a9/gistfile1.json

}