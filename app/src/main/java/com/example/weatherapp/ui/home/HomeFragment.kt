package com.example.weatherapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.retrofit.service.WeatherAPIService
import com.example.weatherapp.retrofit.viewmodel.MainViewModel

class HomeFragment : Fragment() {


    private lateinit var weatherViewModel: MainViewModel
    private lateinit var get: SharedPreferences
    private lateinit var set: SharedPreferences.Editor
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    lateinit var weatherDesc: TextView
    lateinit var degrees: TextView
    lateinit var cityName: TextView
    lateinit var humidity: TextView
    lateinit var windSpeed: TextView
    lateinit var maxTemp: TextView
    lateinit var minTemp: TextView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        get = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)!!
        set = get.edit()

        weatherDesc = binding.weatherDesc
        degrees = binding.degrees
        cityName = binding.cityName
        humidity = binding.humidity
        windSpeed = binding.windSpeed
        maxTemp = binding.maxTemp
        minTemp = binding.minTemp
        degrees = binding.degrees


        weatherViewModel = MainViewModel("Saint petersburg")

        weatherViewModel.refreshData()

        getLiveData()
        return root
    }

    @SuppressLint("SetTextI18n")
    private fun getLiveData() {
        weatherViewModel.weatherData.observe(requireActivity(), Observer { data ->
            data.let {
                degrees.text = data.main.temp.toString()[0].toString() + data.main.temp.toString()[1].toString() + "Cº"
                weatherDesc.text = data.weather.get(0).description.toString()
                cityName.text = data.name
                humidity.text = "humidity: " + data.main.humidity.toString()
                windSpeed.text = "wind speed: " + data.wind.speed.toString()
                maxTemp.text = "max temperature: " + data.main.tempMax.toString()
                minTemp.text = "min temperature: " + data.main.tempMin.toString()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}