package com.ichwan.schoolreport.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemCategoryBinding
import com.ichwan.schoolreport.model.CategoryActivity

class ActivityStudentAdapter(var categories: Array<CategoryActivity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    class ActivityStudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemCategory: ItemCategoryBinding

        fun bind(categoryActivity: CategoryActivity, sharedPool: RecyclerView.RecycledViewPool) {
            itemCategory = ItemCategoryBinding.bind(view)
            itemCategory.categoryTitleTv.text = categoryActivity.category

            val context = itemCategory.root.context
            val childLayoutManager = LinearLayoutManager(context)
            childLayoutManager.initialPrefetchItemCount = 4

            val rv = itemCategory.listItemQuestionRv
            rv.layoutManager = childLayoutManager
            rv.adapter = QuestionActivityAdapter(categoryActivity.questions)
            rv.setRecycledViewPool(sharedPool)
            rv.setHasFixedSize(true)
            rv.isNestedScrollingEnabled = false
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
        (holder as ActivityStudentViewHolder).bind(categories[position], viewPool)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}