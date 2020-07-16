package com.harrycampaz.carshop.data.car

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.harrycampaz.carshop.model.Car

@Dao
interface CarDao {

    @Insert
    suspend fun insert(car: Car)

    @Query("SELECT * FROM car")
    fun getAllCars(): LiveData<List<Car>>
}