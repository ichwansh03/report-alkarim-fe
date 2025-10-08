package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemReportWeeklyValueBinding
import com.ichwan.schoolreport.model.ActivityReport

class ReportStudentAdapter(var report: Array<ActivityReport>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ReportStudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var binding: ItemReportWeeklyValueBinding

        fun bind(item: ActivityReport) {
            binding = ItemReportWeeklyValueBinding.bind(view)
            binding.reportQuestionTv.text = item.question
            binding.markActivityTv.text = item.score
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ItemReportWeeklyValueBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return ReportStudentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ReportStudentViewHolder).bind(report[position])
    }

    override fun getItemCount(): Int {
        return report.size
    }
}