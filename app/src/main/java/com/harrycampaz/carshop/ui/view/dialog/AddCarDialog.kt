package com.harrycampaz.carshop.ui.view.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.data.category.Categories
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.Category
import kotlinx.android.synthetic.main.add_car_dialog.*


private const val TAG = "AddCarDialog"
class AddCarDialog(context: Context, val category: Category): AppCompatDialog(context) {
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
            if(et_add_car_model.text.isNotEmpty() && et_add_price.text.isNotEmpty()
                && et_num_set.text.isNotEmpty() && etd_date_release.text.isNotEmpty() && (isSpecifyCategory && et_specify.text.isNotEmpty())){

                val car = Car(
                    price = et_add_price.text.toString().toDouble(),
                    model = et_add_car_model.text.toString(),
                    seat = et_num_set.text.toString().toInt(),
                    dateRelease = etd_date_release.text.toString().toInt(),
                    categoryId = category.id,
                    isNew = isNew
                )
                Log.e(TAG, "onCreate: $car")
                dismiss()

            }else {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }



    fun setupSpecifyCategory(): Boolean {
        when(category.id){
            Categories.ELECTRIC.id -> {
                et_specify.hint = "Capacidad Bateria "
                return true
            }
            Categories.TRUCK.id -> {
                et_specify.hint = "Max Carga"
                return true
            }
            Categories.COMMERCIAL.id -> {
                et_specify.hint = "Espacio Capacidad"
                return true
            }
            else -> {
                et_specify.visibility = View.INVISIBLE
                return false
            }
        }
    }

}