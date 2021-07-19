package com.example.weatherapp.ui.Locations

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.LocationItemBinding
import com.example.weatherapp.retrofit.viewmodel.MainViewModel

class LocationsRVAdapter constructor(private val fragment : Fragment): RecyclerView.Adapter<LocationsRVAdapter.LocationViewHolder>() {

    private var list = mutableListOf<LocationItem?>()
    lateinit var viewGroup: View;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(inflater,parent,false)
        viewGroup = parent
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {

        holder.weatherViewModel = MainViewModel(list[position]?.cityName!!)
        holder.weatherViewModel.refreshData()

        lateinit var cityName: String
        lateinit var desc: String
        lateinit var degrees: String
        lateinit var humidity: String
        lateinit var windSpeed: String
        lateinit var maxTemp: String
        lateinit var minTemp: String


        holder.weatherViewModel.weatherData.observe(fragment.requireActivity(), Observer { data ->
            data.let {

                degrees = data.main.temp.toString() + "Cº"
                desc = data.weather.get(0).description.toString()
                cityName = data.name
                humidity = data.main.humidity.toString()
                windSpeed = data.wind.speed.toString()
                maxTemp = data.main.tempMax.toString()
                minTemp = data.main.tempMin.toString()


                holder.binding.locationCityNameTitle.text = cityName
                holder.binding.locationDegreesTextView.text = degrees
                holder.binding.locationDescTextView.text = desc
            }
        })

        holder.binding.itemView.setOnClickListener {
            val intent = Intent(fragment.activity, WeatherScreenActivity::class.java)
            intent.putExtra("degrees", degrees)
            intent.putExtra("desc", desc)
            intent.putExtra("cityName", cityName)
            intent.putExtra("humidity", humidity)
            intent.putExtra("windSpeed", windSpeed)
            intent.putExtra("maxTemp", maxTemp)
            intent.putExtra("minTemp", minTemp)

            fragment.activity?.startActivity(intent)
        }
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