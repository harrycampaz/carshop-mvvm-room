package com.harrycampaz.carshop.model

import androidx.room.Entity
import java.io.Serializable
import java.util.*

@Entity(
    tableName = "car"
)
data class Car (
    var id: Int,
    var seat: Int,
    var isNew: Boolean,
    var model:String,
    var dateRelease: Date

): Serializable