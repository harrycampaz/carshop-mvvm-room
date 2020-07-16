package com.harrycampaz.carshop.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.io.Serializable
import java.util.*

@Entity(
    tableName = "car"
)
data class Car (

    @PrimaryKey(autoGenerate = true)
    var id: Int ? = 0,
    var seat: Int?,
    var isNew: Boolean?,
    var model: String?,
    var dateRelease: Date?,
//    @Ignore
//    var category: Category?,

    var batteryCapacity: Double?,
    var maxPayload: Double?,
    var spaceCapacity: Double?
//    @ForeignKey(entity = Category::class,parentColumns = ["id"], childColumns = ["categoryId"], onDelete = CASCADE)
//    var categoryId: Int

)