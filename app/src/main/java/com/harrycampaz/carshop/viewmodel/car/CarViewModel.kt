package com.harrycampaz.carshop.viewmodel.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harrycampaz.carshop.data.car.CarRepository
import com.harrycampaz.carshop.data.category.CategoryRepository
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(val carRepository: CarRepository, val categoryRepository: CategoryRepository): ViewModel() {

    var resultNotFound = MutableLiveData<Boolean>()

    var allCategories:  LiveData<List<Category>> = categoryRepository.getCategories()


    fun insert(car: Car) = CoroutineScope(Dispatchers.Main).launch {
        carRepository.insert(car)
    }

    fun getAllCars() = carRepository.getAllCars()


    fun getCarWithCategory() = carRepository.getCarWithCategory()

    fun isResultNotFound() {
        resultNotFound.value = true
    }


}