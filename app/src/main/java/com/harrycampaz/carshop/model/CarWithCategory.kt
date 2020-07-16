package com.harrycampaz.carshop.model

import androidx.room.Embedded
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.Category

class CarWithCategory {

    @Embedded
    var car: Car? = null

    @Embedded
    var category: Category? = null
}