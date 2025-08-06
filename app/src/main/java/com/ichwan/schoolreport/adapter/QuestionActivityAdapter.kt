package com.ichwan.schoolreport.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemCheckboxBinding
import com.ichwan.schoolreport.model.ActivityReport

class QuestionActivityAdapter(var context: Context, var activity: List<ActivityReport>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class QuestionActivityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemActivity: ItemCheckboxBinding

        fun bind(question: String, action: Boolean) {
            itemActivity = ItemCheckboxBinding.bind(view)
            itemActivity.questionTv.text = question
            itemActivity.questionCb.isChecked = action
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(com.ichwan.schoolreport.R.layout.item_checkbox, parent, false)
        return QuestionActivityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as QuestionActivityViewHolder).bind(
            activity[position].question,
            activity[position].action == "1"
        )
    }

    override fun getItemCount(): Int {
        return activity.size
    }
}