package com.ichwan.schoolreport.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemCategoryBinding
import com.ichwan.schoolreport.model.ActivityReport
import java.util.ArrayList

class ActivityStudentAdapter(var context: Context, var questions: ArrayList<ActivityReport>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class activityStudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemCategory: ItemCategoryBinding

        fun bind(context: Context, category: String, questions: List<ActivityReport>) {
            itemCategory = ItemCategoryBinding.bind(view)
            itemCategory.categoryTitleTv.text = category

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {

    }
}