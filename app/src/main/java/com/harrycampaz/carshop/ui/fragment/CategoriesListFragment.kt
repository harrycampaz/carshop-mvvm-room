package com.harrycampaz.carshop.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.model.Category
import com.harrycampaz.carshop.ui.adapter.CategoryAdapter
import com.harrycampaz.carshop.ui.dialog.AddCategory
import com.harrycampaz.carshop.ui.listener.AddCategoryDialogListener
import com.harrycampaz.carshop.ui.viewmodel.category.CategoryViewModel
import com.harrycampaz.carshop.ui.viewmodel.category.CategoryViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_categories_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

private const val TAG = "CategoriesListFragment"

class CategoriesListFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val factory: CategoryViewModelProviderFactory by instance<CategoryViewModelProviderFactory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // val viewModel = ViewModelProviders.of(this, factory).get(CategoryViewModel::class.java)
        val viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)

        val adapter = CategoryAdapter(listOf())
        rv_category.layoutManager = LinearLayoutManager(context)
        rv_category.adapter = adapter

        viewModel.getCategories().observe(viewLifecycleOwner, Observer {categories ->

            adapter.items = categories
            adapter.notifyDataSetChanged()
        })

        fab_category.setOnClickListener {
            Log.e(TAG, "onViewCreated: Clien en" )
            context?.let { contextSafe ->
                AddCategory(contextSafe, object : AddCategoryDialogListener {
                    override fun onAddButtonClicked(item: Category) {
                        viewModel.insert(item)
                    }
                }).show()
            }


        }
    }
}