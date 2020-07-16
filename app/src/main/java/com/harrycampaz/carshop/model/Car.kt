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
    var id: Int ? = null,
    var seat: Int?,
    var isNew: Boolean?,
    var model: String?,
    var price: Double?,
    var dateRelease: Int?,
//    @Ignore
//    var category: Category?,
    var categoryId: Int?,

    var batteryCapacity: Double? = null,
    var maxPayload: Double? = null,
    var spaceCapacity: Double? = null
//    @ForeignKey(entity = Category::class,parentColumns = ["id"], childColumns = ["categoryId"], onDelete = CASCADE)
//    var categoryId: Int

)