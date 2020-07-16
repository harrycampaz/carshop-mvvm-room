package com.harrycampaz.carshop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String
)