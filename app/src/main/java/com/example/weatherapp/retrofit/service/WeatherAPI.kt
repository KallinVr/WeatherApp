package com.example.weatherapp.retrofit.service

import com.example.weatherapp.retrofit.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET

//https://api.openweathermap.org/data/2.5/weather?q=Saint%20Petersburg&appid=e89690adf00dede235b7c4df6198ea1c&units=metric

interface WeatherAPI {

    @GET("data/2.5/weather?q=Saint%20Petersburg&appid=e89690adf00dede235b7c4df6198ea1c&units=metric")
    fun getData() : Single<WeatherModel>

}