package com.example.weatherapp.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.retrofit.service.WeatherAPIService
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val city: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            MainViewModel(city) as T
        } else {
            throw IllegalArgumentException("ViewModel is not founded")
        }
    }
}