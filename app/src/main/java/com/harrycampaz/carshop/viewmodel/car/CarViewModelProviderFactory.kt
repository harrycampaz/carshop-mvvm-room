package com.harrycampaz.carshop.viewmodel.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harrycampaz.carshop.data.car.CarRepository

class CarViewModelProviderFactory(val carRepository: CarRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  CarViewModel(carRepository) as T
    }
}