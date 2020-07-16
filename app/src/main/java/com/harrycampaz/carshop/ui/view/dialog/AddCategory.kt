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
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.ui.listener.AddCategoryDialogListener
import kotlinx.android.synthetic.main.add_car_dialog.*
import kotlinx.android.synthetic.main.add_category_dialog.*

private const val TAG = "AddCategory"
class AddCategory(context: Context, var addCategoryDialogListener: AddCategoryDialogListener): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_category_dialog)



        btn_cancel.setOnClickListener {
            dismiss()
        }

        btn_send.setOnClickListener {

            val name = et_category_name.text.toString()

            if(name.isEmpty()){
                Toast.makeText(context, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = Category(name = name)
            addCategoryDialogListener.onAddButtonClicked(item)
            dismiss()
        }

    }
}