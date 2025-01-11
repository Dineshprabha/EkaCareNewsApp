package com.dinesh.ekacarenewsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dinesh.ekacarenewsapp.domain.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class NewsTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromSource(source: Source?): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String): Source? {
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(sourceString, type)
    }
}