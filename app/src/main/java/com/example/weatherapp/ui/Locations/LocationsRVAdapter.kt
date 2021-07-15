package com.example.weatherapp.ui.Locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.LocationItemBinding
import com.example.weatherapp.retrofit.service.WeatherAPIService
import com.example.weatherapp.retrofit.viewmodel.MainViewModel

class LocationsRVAdapter constructor(private val c : Fragment): RecyclerView.Adapter<LocationsRVAdapter.LocationViewHolder>() {

    private var list = mutableListOf<LocationItem?>()
    lateinit var viewGroup: View;


    @JvmName("setNewsList1")
    fun setNewsList(news: List<LocationItem?>){
        this.list = news.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(inflater,parent,false)
        viewGroup = parent
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {

        holder.weatherViewModel = MainViewModel(WeatherAPIService("moscow"))
        holder.weatherViewModel.refreshData()


        holder.weatherViewModel.weatherData.observe(c.requireActivity(), Observer { data ->
            data.let {
                holder.binding.locationCityNameTitle.text = data.toString()
            }
        })
    }

    override fun getItemCount(): Int = list.size


    class LocationViewHolder(val binding: LocationItemBinding): RecyclerView.ViewHolder(binding.root){
        lateinit var weatherViewModel: MainViewModel
    }

    fun setData(temp: MutableList<LocationItem?>){
        list = temp
        notifyDataSetChanged()
    }


}