package com.harrycampaz.carshop.ui.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.data.category.Categories
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.ui.listener.OnItemCarListener
import kotlinx.android.synthetic.main.add_car_dialog.*


private const val TAG = "AddCarDialog"
class AddCarDialog(context: Context, val category: Category, val onItemCarListener: OnItemCarListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_car_dialog)


        var isNew = true

        tv_title_add_car.text = "Agregar Carro: ${category.name.toUpperCase()}"
        var isSpecifyCategory = setupSpecifyCategory()

        val optionIsNew = context.resources.getStringArray(R.array.isNew)

        val adapter = ArrayAdapter(context,
            android.R.layout.simple_spinner_item, optionIsNew)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                isNew = optionIsNew[position] == "Si"
            }

        }

        btn_cancel_car.setOnClickListener {
            dismiss()
        }

        btn_send_car.setOnClickListener {
            if (isSpecifyCategory){
                sendToSpecifyCategory(isNew)
            }else {
                sendToGenericCategory(isNew)
            }
        }
    }

    fun sendToSpecifyCategory(isNew: Boolean){
        if(et_add_car_model.text.isNotEmpty() && et_add_price.text.isNotEmpty()
            && validateSeat(et_num_set.text.toString()) && validateYear(etd_date_release.text.toString()) && et_specify.text.isNotEmpty()){

            val car = Car(
                price = et_add_price.text.toString().toDouble(),
                model = et_add_car_model.text.toString(),
                seat = et_num_set.text.toString().toInt(),
                dateRelease = etd_date_release.text.toString().toInt(),
                categoryId = category.id,
                isNew = isNew
            )

            sendSpecifyCategory(car)

        } else {
            Toast.makeText(context, "Datos no validos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendToGenericCategory(isNew: Boolean){
        if(et_add_car_model.text.isNotEmpty() && et_add_price.text.isNotEmpty()
            && validateSeat(et_num_set.text.toString()) && validateYear(etd_date_release.text.toString())){

            val car = Car(
                price = et_add_price.text.toString().toDouble(),
                model = et_add_car_model.text.toString(),
                seat = et_num_set.text.toString().toInt(),
                dateRelease = etd_date_release.text.toString().toInt(),
                categoryId = category.id,
                isNew = isNew
            )
            onItemCarListener.onItemCarClicked(car)
            dismiss()

        } else {
            Toast.makeText(context, "Datos no validos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateYear(year: String) : Boolean{

        return try {
            val parseYear = year.toInt()
            parseYear in 1903..2021
        }catch(e: NumberFormatException){
            false
        }

    }

    private fun validateSeat(seat: String) : Boolean{

        return try {
            val parseSeat = seat.toInt()
            parseSeat in 1..15

        }catch (e: NumberFormatException){
            false
        }

    }
    private fun sendSpecifyCategory(car : Car) {
        when(category.id){
            Categories.ELECTRIC.id -> {
                car.batteryCapacity = et_specify.text.toString().toDouble()
                onItemCarListener.onItemCarClicked(car)
                dismiss()
            }
            Categories.TRUCK.id -> {
                car.maxPayload = et_specify.text.toString().toDouble()
                onItemCarListener.onItemCarClicked(car)
                dismiss()
            }
            Categories.COMMERCIAL.id -> {
                car.spaceCapacity = et_specify.text.toString().toDouble()
                onItemCarListener.onItemCarClicked(car)
                dismiss()
            }
            else -> {
                dismiss()
            }
        }
    }


    private fun setupSpecifyCategory(): Boolean {
        when(category.id){
            Categories.ELECTRIC.id -> {
                et_specify.hint = "Capacidad Bateria (w) "
                return true
            }
            Categories.TRUCK.id -> {
                et_specify.hint = "Max Carga (Tm)"
                return true
            }
            Categories.COMMERCIAL.id -> {
                et_specify.hint = "Espacio Capacidad (m2)"
                return true
            }
            else -> {
                et_specify.visibility = View.INVISIBLE
                return false
            }
        }
    }

}