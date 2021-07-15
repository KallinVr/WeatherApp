package com.example.weatherapp.ui.Locations

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentLocationsBinding
import com.example.weatherapp.retrofit.service.WeatherAPIService
import com.example.weatherapp.retrofit.viewmodel.MainViewModel
import com.example.weatherapp.retrofit.viewmodel.ViewModelFactory

class LocationsFragment : Fragment() {

    private lateinit var locationsViewModel: LocationsViewModel
    private var _binding: FragmentLocationsBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationsViewModel =
            ViewModelProvider(this).get(LocationsViewModel::class.java)

        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.locationRv
        val linearlayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearlayoutManager

        var adapter = LocationsRVAdapter(this)

        var viewModel = ViewModelProvider(this, ViewModelFactory(WeatherAPIService("moscow")))
            .get(MainViewModel::class.java)
        viewModel.weatherData.observe(viewLifecycleOwner, Observer{
            adapter.setData(insertData())
        })

        recyclerView.adapter = adapter
        adapter.setData(insertData())

        return root
    }

    private fun insertData(): MutableList<LocationItem?> {
        val list = mutableListOf<LocationItem?>()

        list.add(LocationItem("Moscow", "test", "test", "test", "test", "test"))
        list.add(LocationItem("Prague", "test", "test", "test", "test", "test"))
        list.add(LocationItem("Bejing", "test", "test", "test", "test", "test"))
        list.add(LocationItem("Tokyo", "test", "test", "test", "test", "test"))
        list.add(LocationItem("Surgut", "test", "test", "test", "test", "test"))

        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}