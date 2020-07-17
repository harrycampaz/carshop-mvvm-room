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
import com.harrycampaz.carshop.ui.listener.OnItemCarListener
import kotlinx.android.synthetic.main.add_car_dialog.*
import kotlinx.android.synthetic.main.edit_car_dialog.*
import java.util.*


private const val TAG = "AddCarDialog"
class EditCarDialog(context: Context, private val carEdit: Car, private val onItemCarListener: OnItemCarListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_car_dialog)


        var isNew = true

        val isSpecifyCategory = setupSpecifyCategory()

        loadCar()

        val optionIsNew = context.resources.getStringArray(R.array.isNew)

        val adapter = ArrayAdapter(context,
            android.R.layout.simple_spinner_item, optionIsNew)
        spinner_edit.adapter = adapter

        spinner_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                isNew = optionIsNew[position] == "Si"
            }

        }

        btn_cancel_edit.setOnClickListener {
            dismiss()
        }

        btn_send_edit.setOnClickListener {
            if (isSpecifyCategory){
                sendToSpecifyCategory(isNew)
            }else {
                sendToGenericCategory(isNew)
            }
        }
    }

    fun loadCar(){
        et_edit_car_model.setText(carEdit.model.toString())
        et_edit_price.setText(carEdit.price.toString())
        et_num_set_edit.setText(carEdit.seat.toString())
        etd_date_release_edit.setText(carEdit.dateRelease.toString())
    }

    fun sendToSpecifyCategory(isNew: Boolean){
        if(et_edit_car_model.text.isNotEmpty() && et_edit_price.text.isNotEmpty()
            && validateSeat(et_num_set_edit.text.toString()) && validateYear(etd_date_release_edit.text.toString()) && et_specify_edit.text.isNotEmpty()){

            val car = Car(
                id = carEdit.id,
                price = et_edit_price.text.toString().toDouble(),
                model = et_edit_car_model.text.toString(),
                seat = et_num_set_edit.text.toString().toInt(),
                dateRelease = etd_date_release_edit.text.toString().toInt(),
                categoryId = carEdit.categoryId,
                isNew = isNew
            )

            sendSpecifyCategory(car)

        } else {
            Toast.makeText(context, "Datos no validos", Toast.LENGTH_SHORT).show()
        }
    }



    private fun sendToGenericCategory(isNew: Boolean){
        if(et_edit_car_model.text.isNotEmpty() && et_edit_price.text.isNotEmpty()
            && validateSeat(et_num_set_edit.text.toString()) && validateYear(etd_date_release_edit.text.toString())){

            val car = Car(
                id = carEdit.id,
                price = et_edit_price.text.toString().toDouble(),
                model = et_edit_car_model.text.toString(),
                seat = et_num_set_edit.text.toString().toInt(),
                dateRelease = etd_date_release_edit.text.toString().toInt(),
                categoryId = carEdit.categoryId,
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
        when(carEdit.categoryId){
            Categories.ELECTRIC.id -> {
                car.batteryCapacity = et_specify_edit.text.toString().toDouble()
                onItemCarListener.onItemCarClicked(car)
                dismiss()
            }
            Categories.TRUCK.id -> {
                car.maxPayload = et_specify_edit.text.toString().toDouble()
                onItemCarListener.onItemCarClicked(car)
                dismiss()
            }
            Categories.COMMERCIAL.id -> {
                car.spaceCapacity = et_specify_edit.text.toString().toDouble()
                onItemCarListener.onItemCarClicked(car)
                dismiss()
            }
            else -> {
                dismiss()
            }
        }
    }


    private fun setupSpecifyCategory(): Boolean {
        when(carEdit.categoryId){
            Categories.ELECTRIC.id -> {
                et_specify_edit.hint = "Capacidad Bateria (w) "
               et_specify_edit.setText(carEdit.batteryCapacity.toString())
                return true
            }
            Categories.TRUCK.id -> {
                et_specify_edit.hint = "Max Carga (Tm)"

                et_specify_edit.setText(carEdit.maxPayload.toString())
                return true
            }
            Categories.COMMERCIAL.id -> {
                et_specify_edit.hint = "Espacio Capacidad (m2)"

                et_specify_edit.setText(carEdit.spaceCapacity.toString())
                return true
            }
            else -> {
                et_specify_edit.visibility = View.INVISIBLE
                return false
            }
        }
    }

}