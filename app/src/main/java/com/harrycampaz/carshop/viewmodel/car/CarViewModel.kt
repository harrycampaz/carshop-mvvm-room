package com.harrycampaz.carshop.viewmodel.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harrycampaz.carshop.data.car.CarRepository
import com.harrycampaz.carshop.model.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(val carRepository: CarRepository): ViewModel() {

    var resultNotFound = MutableLiveData<Boolean>()



    fun upsert(car: Car) = CoroutineScope(Dispatchers.Main).launch {
        carRepository.upsert(car)
    }



    fun getCarWithCategory() = carRepository.getCarWithCategory()

    fun isResultNotFound() {
        resultNotFound.value = true
    }


}