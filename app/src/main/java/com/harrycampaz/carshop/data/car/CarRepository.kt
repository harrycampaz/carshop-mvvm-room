package com.harrycampaz.carshop.data.car

import com.harrycampaz.carshop.data.DatabaseHelper
import com.harrycampaz.carshop.model.Car

class CarRepository(private val databaseHelper: DatabaseHelper){

    suspend fun insert(car: Car) = databaseHelper.carDao().insert(car)
    fun getAllCars()= databaseHelper.carDao().getAllCars()

}