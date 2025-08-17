package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemReportWeeklyBinding
import com.ichwan.schoolreport.model.CategoryActivity

class ReportStudentCategoryAdapter(var categories: Array<CategoryActivity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    class ReportStudentCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemReport: ItemReportWeeklyBinding

        fun studentBind(categoryActivity: CategoryActivity, sharedPool: RecyclerView.RecycledViewPool) {
            itemReport = ItemReportWeeklyBinding.bind(view)
            itemReport.reportCategoryTv.text = categoryActivity.category
            itemReport.markCategoryTv.text = categoryActivity.mark

            val context = itemReport.root.context
            val childLayoutManager = LinearLayoutManager(context)
            childLayoutManager.initialPrefetchItemCount = 4

            val rv = itemReport.listItemQuestionRv
            rv.layoutManager = childLayoutManager
            rv.adapter = ReportStudentAdapter(categoryActivity.questions)
            rv.setHasFixedSize(true)
            rv.setRecycledViewPool(sharedPool)
            rv.isNestedScrollingEnabled = false
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ItemReportWeeklyBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return ReportStudentCategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ReportStudentCategoryViewHolder).studentBind(categories[position], viewPool)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}