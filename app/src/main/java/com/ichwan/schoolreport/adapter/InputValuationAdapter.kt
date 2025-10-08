package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemValuationInputBinding
import com.ichwan.schoolreport.model.ActivityReport

class InputValuationAdapter(var report: Array<ActivityReport>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class InputValuationViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var binding: ItemValuationInputBinding

        fun bind(item: ActivityReport) {
            binding = ItemValuationInputBinding.bind(view)
            binding.valuationQuestionTv.text = item.question
            val adapter = binding.valueItemsSp.adapter
            var position = 0
            for (i in 0..adapter.count - 1) {
                if (adapter.getItem(i).toString() == item.score) {
                    position = i
                    break
                }
            }
            binding.valueItemsSp.setSelection(position)

            // optional: listen for changes to update model
            binding.valueItemsSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    item.score = parent.getItemAtPosition(pos).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ItemValuationInputBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return InputValuationViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as InputValuationViewHolder).bind(report[position])
    }

    override fun getItemCount(): Int {
        return report.size
    }
}