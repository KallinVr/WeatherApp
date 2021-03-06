package com.example.weatherapp.ui.Locations

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentLocationsBinding
import com.example.weatherapp.db.DBHelper

class LocationsFragment : Fragment() {

    private lateinit var locationsViewModel: LocationsViewModel
    private var _binding: FragmentLocationsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LocationsRVAdapter

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

        recyclerView = binding.locationRv
        val addCityButton = binding.addCityButton
        val linearlayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearlayoutManager

        adapter = LocationsRVAdapter(this)

        recyclerView.adapter = adapter
        adapter.setData(insertData())

        addCityButton.setOnClickListener {
            val intent = Intent(activity, AddCityActivity::class.java)
            startActivity(intent)
            recyclerView.adapter?.notifyDataSetChanged()
        }

        return root
    }

    @SuppressLint("Recycle")
    fun insertData(): MutableList<LocationItem?> {

        var dbHelper = DBHelper(activity)
        val database = dbHelper.writableDatabase
        val list = mutableListOf<LocationItem?>()

        val cursor: Cursor =
            database.query(DBHelper.TABLE_CITIES, null, null, null, null, null, null)


        val idIndex: Int = cursor.getColumnIndex(DBHelper.KEY_ID)
        val nameIndex: Int = cursor.getColumnIndex(DBHelper.KEY_NAME)

        while (cursor.moveToNext()) {
            Log.d(
                "mLog",
                "ID = " + cursor.getInt(idIndex).toString() + ", name = " + cursor.getString(
                    nameIndex
                ).toString()
            )
            list.add(LocationItem(cursor.getString(nameIndex).toString()))
        }

        return list
    }

    override fun onResume() {
        super.onResume()
        adapter.setData(insertData())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}