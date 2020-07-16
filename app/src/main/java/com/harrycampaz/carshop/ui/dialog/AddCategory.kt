package com.harrycampaz.carshop.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.ui.listener.AddCategoryDialogListener
import kotlinx.android.synthetic.main.add_category_dialog.*

class AddCategory(context: Context, var addCategoryDialogListener: AddCategoryDialogListener): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_category_dialog)

        btn_cancel.setOnClickListener {
            dismiss()
        }

        btn_send.setOnClickListener {

            val name = et_category.text.toString()

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