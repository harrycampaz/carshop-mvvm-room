package com.harrycampaz.carshop.data.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.harrycampaz.carshop.model.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<Category>>

}
