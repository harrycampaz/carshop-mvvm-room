package com.harrycampaz.carshop.ui.viewmodel.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.harrycampaz.carshop.data.category.CategoryRepository
import com.harrycampaz.carshop.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository):ViewModel() {

    fun insert(category: Category) = CoroutineScope(Dispatchers.Main).launch {
        categoryRepository.insert(category)
    }


    fun getCategories() = categoryRepository.getCategories()


}