package com.harrycampaz.carshop.ui.viewmodel.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harrycampaz.carshop.data.category.CategoryRepository

class CategoryViewModelProviderFactory(
    private val categoryRepository: CategoryRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(
            categoryRepository
        ) as T
    }

}