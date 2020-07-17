package com.harrycampaz.carshop.viewmodel.category

import androidx.lifecycle.ViewModel
import com.harrycampaz.carshop.data.car.CarRepository
import com.harrycampaz.carshop.data.category.CategoryRepository
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository, private val carRepository: CarRepository):ViewModel() {

    fun insert(category: Category) = CoroutineScope(Dispatchers.Main).launch {
        categoryRepository.insert(category)
    }

    fun insertCarByCategory(car: Car) = CoroutineScope(Dispatchers.Main).launch {
        carRepository.upsert(car)
    }

    fun getCategories() = categoryRepository.getCategories()


}