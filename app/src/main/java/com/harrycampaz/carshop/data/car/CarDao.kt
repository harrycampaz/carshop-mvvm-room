package com.harrycampaz.carshop.data.car

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.CarWithCategory

@Dao
interface CarDao {

    @Insert
    suspend fun insert(car: Car)

    @Query("SELECT * FROM car")
    fun getAllCars(): LiveData<List<Car>>

    @Query("SELECT * from car INNER JOIN category ON car.categoryId = category.idCategory")
    fun getCarWithCategory(): LiveData<List<CarWithCategory>>
}