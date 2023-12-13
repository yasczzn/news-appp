package com.example.newsapp.data.db

import androidx.room.TypeConverter
import com.example.newsapp.data.model.Sources

class Converters {

    @TypeConverter
    fun fromSource(sources: Sources): String {
        return sources.name
    }

    @TypeConverter
    fun toSource(name: String): Sources {
        return Sources(name, name)
    }
}