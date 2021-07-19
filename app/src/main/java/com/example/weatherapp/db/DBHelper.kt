package com.example.weatherapp.db


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + TABLE_CITIES + "(" + KEY_ID
                    + " integer primary key," + KEY_NAME + " text" + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE_CITIES")
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "citiesDb"
        const val TABLE_CITIES = "cities"
        const val KEY_ID = "_id"
        const val KEY_NAME = "name"
    }
}