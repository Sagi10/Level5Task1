package com.lalee.level5task1.model

import androidx.room.*
import java.util.*

@Entity
class Note(

    var title: String,
    var text: String,
    var lastUpdated: Date,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
)

class Converters{

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
