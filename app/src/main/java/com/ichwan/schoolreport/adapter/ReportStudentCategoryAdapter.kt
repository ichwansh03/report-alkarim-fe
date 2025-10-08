package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemReportWeeklyBinding
import com.ichwan.schoolreport.model.CategoryActivity

class ReportStudentCategoryAdapter(var categories: Array<CategoryActivity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ReportStudentCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemReport: ItemReportWeeklyBinding

        fun studentBind(categoryActivity: CategoryActivity) {
            itemReport = ItemReportWeeklyBinding.bind(view)
            itemReport.reportCategoryTv.text = categoryActivity.category
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
        (holder as ReportStudentCategoryViewHolder).studentBind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}