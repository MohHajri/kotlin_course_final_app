package com.ddevs.nasaapod

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApodService {
    @GET("apod")
    suspend fun getTodayApod(@Query("api_key") apiKey:String,@Query("thumbs") thumbs:Boolean=true): Apod

    @GET("apod")
    suspend fun getList(@Query("api_key")apiKey: String,@Query("thumbs") thumbs:Boolean=true,@Query("count") count:Int =15):List<Apod>
}

object ApodAPI{
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.nasa.gov/planetary/").addConverterFactory(GsonConverterFactory.create()).build()
    val service:ApodService=retrofit.create(ApodService::class.java)
}