package com.harrycampaz.carshop.viewmodel.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harrycampaz.carshop.data.car.CarRepository
import com.harrycampaz.carshop.data.category.CategoryRepository

class CategoryViewModelProviderFactory(
    private val categoryRepository: CategoryRepository, private val carRepository: CarRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(
            categoryRepository, carRepository
        ) as T
    }

}