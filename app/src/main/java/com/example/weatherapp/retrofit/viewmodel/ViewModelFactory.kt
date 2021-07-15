package com.example.weatherapp.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.retrofit.service.WeatherAPIService
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val repository: WeatherAPIService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            MainViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not founded")
        }
    }
}