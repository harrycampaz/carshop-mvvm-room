package com.harrycampaz.carshop

import android.app.Application
import com.harrycampaz.carshop.data.DatabaseHelper
import com.harrycampaz.carshop.data.car.CarRepository
import com.harrycampaz.carshop.data.category.CategoryRepository
import com.harrycampaz.carshop.ui.viewmodel.car.CarViewModelProviderFactory
import com.harrycampaz.carshop.ui.viewmodel.category.CategoryViewModelProviderFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

import org.kodein.di.generic.singleton

class BaseApplication: Application(), KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@BaseApplication))
            bind() from  singleton { DatabaseHelper(instance()) }
            bind() from singleton { CategoryRepository(instance()) }
            bind() from singleton { CarRepository(instance()) }
            bind() from provider {
                CategoryViewModelProviderFactory(
                    instance()
                )
            }
            bind() from provider {
                CarViewModelProviderFactory(
                    instance()
                )
            }
        }
}