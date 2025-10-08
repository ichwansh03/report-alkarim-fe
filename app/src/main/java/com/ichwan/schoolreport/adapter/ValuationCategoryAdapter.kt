package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemValuationBinding
import com.ichwan.schoolreport.model.CategoryActivity

class ValuationCategoryAdapter(var categories: Array<CategoryActivity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ValuationCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var item : ItemValuationBinding

        fun bind (category: CategoryActivity) {
            item = ItemValuationBinding.bind(view)
            item.categoryValuationTv.text = category.category

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ItemValuationBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return ValuationCategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ValuationCategoryViewHolder).bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}