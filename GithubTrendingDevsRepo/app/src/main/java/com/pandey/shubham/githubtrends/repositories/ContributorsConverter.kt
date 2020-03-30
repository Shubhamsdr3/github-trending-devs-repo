package com.pandey.shubham.githubtrends.repositories

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pandey.shubham.githubtrends.repositories.data.ContributorsDto
import java.lang.reflect.Type


class ContributorsConverter {

    @TypeConverter
    fun stringToMeasurements(json: String?): List<ContributorsDto?>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<ContributorsDto?>?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<ContributorsDto?>?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<ContributorsDto?>?>() {}.type
        return gson.toJson(list, type)
    }
}