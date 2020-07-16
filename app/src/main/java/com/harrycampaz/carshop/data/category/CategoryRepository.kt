package com.harrycampaz.carshop.data.category

import com.harrycampaz.carshop.data.DatabaseHelper
import com.harrycampaz.carshop.model.Category

class CategoryRepository(private val databaseHelper: DatabaseHelper) {

    suspend fun insert(category: Category) = databaseHelper.categoryDao().insert(category)
    fun getCategories() = databaseHelper.categoryDao().getCategories()

}