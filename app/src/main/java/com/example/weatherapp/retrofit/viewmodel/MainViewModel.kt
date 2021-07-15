package com.example.weatherapp.retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.retrofit.model.WeatherModel
import com.example.weatherapp.retrofit.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: WeatherAPIService): ViewModel() {

    private val weatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()

    val weatherData = MutableLiveData<WeatherModel>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        disposable.add(
            weatherAPIService.getDataService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(t: WeatherModel) {
                        weatherData.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.stackTrace
                        Log.e("INFO", e.toString())
                    }
                })
        )
    }
}