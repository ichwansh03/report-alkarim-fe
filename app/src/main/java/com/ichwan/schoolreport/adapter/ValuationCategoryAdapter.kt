package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemValuationBinding
import com.ichwan.schoolreport.model.CategoryActivity

class ValuationCategoryAdapter(var categories: Array<CategoryActivity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    class ValuationCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var item : ItemValuationBinding

        fun bind (category: CategoryActivity, sharedPool: RecyclerView.RecycledViewPool) {
            item = ItemValuationBinding.bind(view)
            item.categoryValuationTv.text = category.category
            val context = item.root.context
            val childLayoutManager = LinearLayoutManager(context)
            childLayoutManager.initialPrefetchItemCount = 4

            val rv = item.valuationQuestionRv
            rv.layoutManager = childLayoutManager
            rv.adapter = InputValuationAdapter(category.questions)
            rv.setHasFixedSize(true)
            rv.setRecycledViewPool(sharedPool)
            rv.isNestedScrollingEnabled = false
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
        (holder as ValuationCategoryViewHolder).bind(categories[position], viewPool)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}