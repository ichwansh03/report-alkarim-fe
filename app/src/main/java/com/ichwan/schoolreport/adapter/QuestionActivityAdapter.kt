package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemCheckboxBinding
import com.ichwan.schoolreport.model.ActivityReport

class QuestionActivityAdapter(var activity: Array<ActivityReport>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class QuestionActivityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemActivity: ItemCheckboxBinding

        fun bind(item: ActivityReport, onCheckedChanged: (Int, Boolean) -> Unit) {
            itemActivity = ItemCheckboxBinding.bind(view)
            itemActivity.questionTv.text = item.question
            itemActivity.questionCb.setOnCheckedChangeListener(null)
            itemActivity.questionCb.isChecked = item.action
            val pos = bindingAdapterPosition
            itemActivity.questionCb.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChanged(pos, isChecked)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
        return QuestionActivityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = activity[position]
        (holder as QuestionActivityViewHolder).bind(item) { adapterPos, isChecked ->
            if (adapterPos != RecyclerView.NO_POSITION) {
                activity[adapterPos].action = isChecked
                notifyItemChanged(adapterPos)
            }
        }
    }

    override fun getItemCount(): Int {
        return activity.size
    }
}