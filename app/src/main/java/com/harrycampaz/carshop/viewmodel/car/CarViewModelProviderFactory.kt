package com.harrycampaz.carshop.viewmodel.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harrycampaz.carshop.data.car.CarRepository
import com.harrycampaz.carshop.data.category.CategoryRepository

class CarViewModelProviderFactory(val carRepository: CarRepository,val  categoryRepository: CategoryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  CarViewModel(carRepository, categoryRepository) as T
    }
}