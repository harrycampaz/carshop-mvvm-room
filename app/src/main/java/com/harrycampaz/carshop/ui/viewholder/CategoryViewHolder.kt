package com.harrycampaz.carshop.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.ui.listener.AddCarDialogListener
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvName = itemView.tv_category_name

    fun bind(category: Category, addCarDialogListener: AddCarDialogListener){
        tvName.text = category.name.toUpperCase()
        itemView.setOnClickListener {
            addCarDialogListener.onAddButtonClicked(category)
        }
    }

}
