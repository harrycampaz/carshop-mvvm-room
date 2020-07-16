package com.harrycampaz.carshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.ui.listener.ShowFormCarDialogListener
import com.harrycampaz.carshop.ui.viewholder.CategoryViewHolder

class CategoryAdapter(
    var items: List<Category>, val showFormCarDialogListener: ShowFormCarDialogListener): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        val category = items[position]
        viewHolder.bind(category, showFormCarDialogListener)

    }
}