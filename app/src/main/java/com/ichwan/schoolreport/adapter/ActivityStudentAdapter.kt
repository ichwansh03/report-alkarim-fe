package com.ichwan.schoolreport.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemCategoryBinding
import com.ichwan.schoolreport.model.CategoryActivity

class ActivityStudentAdapter(var categories: Array<CategoryActivity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ActivityStudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemCategory: ItemCategoryBinding

        fun bind(categoryActivity: CategoryActivity) {
            itemCategory = ItemCategoryBinding.bind(view)
            itemCategory.categoryTitleTv.text = categoryActivity.category

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ItemCategoryBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        ).root

        return ActivityStudentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ActivityStudentViewHolder).bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}