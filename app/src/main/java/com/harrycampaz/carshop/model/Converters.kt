package com.harrycampaz.carshop.model

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromCategory(category: Category): Int {
        return category.id
    }

    @TypeConverter
    fun toCategory(name: String): Category {
        return  Category(name = name)
    }

     @TypeConverter
    fun  toDate( dateLong: Long): Date? {
         return Date(dateLong)

    }

    @TypeConverter
    fun fromDate(date: Date): Long?{
        return date.time
    }



}