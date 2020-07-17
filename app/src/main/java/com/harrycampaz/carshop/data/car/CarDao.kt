package com.harrycampaz.carshop.data.car

import androidx.lifecycle.LiveData
import androidx.room.*
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.CarWithCategory

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(car: Car)

    @Query("SELECT * from car INNER JOIN category ON car.categoryId = category.idCategory")
    fun getCarWithCategory(): LiveData<List<CarWithCategory>>

    @Update
    suspend fun update(car: Car)
}