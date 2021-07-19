package com.example.weatherapp.ui.Locations

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.db.DBHelper
import com.example.weatherapp.ui.Locations.LocationsRVAdapter

class AddCityActivity : AppCompatActivity() {

    lateinit var addCityToListButton: Button
    lateinit var cityAddEditText: EditText
    lateinit var resultTextView: TextView
    lateinit var dbHelper: DBHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        addCityToListButton = findViewById(R.id.add_city_to_list_button)
        cityAddEditText = findViewById(R.id.city_add_edit_text)
        resultTextView = findViewById(R.id.add_city_result_text_view)

        dbHelper = DBHelper(this)

        addCityToListButton.setOnClickListener {
            val name: String = cityAddEditText.text.toString()

            if (checkIfCityExist(name)){
                val database = dbHelper.writableDatabase
                val contentValues = ContentValues()

                contentValues.put(DBHelper.KEY_NAME, name)
                database.insert(DBHelper.TABLE_CITIES, null, contentValues)
                resultTextView.text = "city successfully added to the list"

            }
        }
    }


    // fix later
    private fun checkIfCityExist(name: String): Boolean {

        return true
    }
}