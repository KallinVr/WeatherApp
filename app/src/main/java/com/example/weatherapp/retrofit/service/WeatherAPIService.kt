package com.example.weatherapp.retrofit.service

import com.example.weatherapp.retrofit.model.WeatherModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


//https://api.openweathermap.org/data/2.5/weather?q=Saint%20Petersburg&appid=e89690adf00dede235b7c4df6198ea1c&units=metric

class WeatherAPIService {

    private val baseUrl = "https://api.openweathermap.org/"
    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java )

    fun getDataService(): Single<WeatherModel>{
        return api.getData()
    }
}