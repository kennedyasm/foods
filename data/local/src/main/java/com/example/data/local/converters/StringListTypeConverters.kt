package com.example.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListTypeConverters {

    @TypeConverter
    fun setStringList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun getStringList(jsonString: String): List<String> {
        return Gson().fromJson(jsonString, object : TypeToken<List<String>>(){}.type)
    }
}
