package com.harrycampaz.carshop.data.car

import com.harrycampaz.carshop.data.DatabaseHelper
import com.harrycampaz.carshop.model.Car

class CarRepository(private val databaseHelper: DatabaseHelper){

    suspend fun upsert(car: Car) = databaseHelper.carDao().upsert(car)


    fun getCarWithCategory() = databaseHelper.carDao().getCarWithCategory()


}