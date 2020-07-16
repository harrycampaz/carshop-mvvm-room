package com.harrycampaz.carshop.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.harrycampaz.carshop.data.car.CarDao
import com.harrycampaz.carshop.data.category.Categories
import com.harrycampaz.carshop.data.category.CategoryDao
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.model.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Category::class, Car::class], version = DatabaseHelper.DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class DatabaseHelper: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun carDao(): CarDao

    companion object {
        private var instance: DatabaseHelper? = null

        const val DATABASE_VERSION = 1
        val DATABASE_NAME = "carshop.db"
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseHelper::class.java,
                DATABASE_NAME)
                .addCallback(callback)
                .build()


        private val callback: Callback = object : Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("DB Creada: ", db.path)

                val electric = Category(Categories.ELECTRIC.id, "electric")
                val truck = Category(Categories.TRUCK.id, "truck")
                val commercial = Category(Categories.COMMERCIAL.id, "commercial")

                instance?.run {
                    CoroutineScope(Dispatchers.IO).launch {
                        categoryDao().insert(category = electric)
                        categoryDao().insert(category = truck)
                        categoryDao().insert(category = commercial)
                    }
                }
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.d("DB Open: ", db.path)
            }

        }
    }

}