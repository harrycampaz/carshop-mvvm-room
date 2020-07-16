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
import java.util.*


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
//
//                var seat: Int?,
//                var isNew: Boolean?,
//                var model: String?,
//                var dateRelease: Date?,

                val carUno = Car(id = 1, seat = 3, price = 20000.0, isNew = true, model = "Mazda", dateRelease = 2010, categoryId = 2)
                val caDos = Car(id = 2, seat = 2, isNew =  false,price = 32000.0, model = "Nissan", dateRelease = 2011, categoryId = 1)

                val carTres = Car(id = 3, seat = 1, isNew = true,price = 4000.0, model = "Lobo", dateRelease = 2092, categoryId = 2)
                val caCuatro = Car(id = 4, seat = 5, isNew = false, price = 25000.0, model = "Homda", dateRelease = 2010, categoryId = 3)

                instance?.run {
                    CoroutineScope(Dispatchers.IO).launch {
                        categoryDao().insert(category = electric)
                        categoryDao().insert(category = truck)
                        categoryDao().insert(category = commercial)

//                        carDao().insert(carUno)
//                        carDao().insert(caDos)
//                        carDao().insert(carTres)
//                        carDao().insert(caCuatro)
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