package com.example.weatherapp.ui.Locations

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.db.DBHelper
import java.util.*

class WeatherScreen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_screen)

        var cityName = intent.getStringExtra("cityName")

        findViewById<TextView>(R.id.weather_screen_degrees).text = intent.getStringExtra("degress")
        findViewById<TextView>(R.id.weather_screen_weather_desc).text = intent.getStringExtra("desc")
        findViewById<TextView>(R.id.weather_screen_cityName).text = cityName
        findViewById<TextView>(R.id.weather_screen_wind_speed).text = "wind speed: " + intent.getStringExtra("windSpeed")
        findViewById<TextView>(R.id.weather_screen_humidity).text = "humidity: " + intent.getStringExtra("humidity")
        findViewById<TextView>(R.id.weather_screen_max_temp).text = "max temperature " + intent.getStringExtra("maxTemp")
        findViewById<TextView>(R.id.weather_screen_min_temp).text = "min temperature " + intent.getStringExtra("minTemp")

        findViewById<ImageView>(R.id.delete_city_button).setOnClickListener {
            val dbHelper = DBHelper(this)
            val database = dbHelper.writableDatabase

            cityName = cityName?.toLowerCase()

            database.execSQL("DELETE FROM cities WHERE name = '$cityName'")
            finish()
        }

    }


}