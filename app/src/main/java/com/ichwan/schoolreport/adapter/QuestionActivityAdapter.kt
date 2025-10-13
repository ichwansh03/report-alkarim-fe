package com.ichwan.schoolreport.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemCheckboxBinding
import com.ichwan.schoolreport.databinding.ItemTextBinding
import com.ichwan.schoolreport.model.Question

class QuestionActivityAdapter(private var questions: List<Question>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CHECKBOX = 1
        private const val VIEW_TYPE_TEXT = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (questions[position].option) {
            "TRUE_FALSE" -> VIEW_TYPE_CHECKBOX
            "DESCRIPTIVE" -> VIEW_TYPE_TEXT
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHECKBOX -> {
                val binding = ItemCheckboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CheckboxViewHolder(binding)
            }
            VIEW_TYPE_TEXT -> {
                val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TextViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val question = questions[position]
        when (holder) {
            is CheckboxViewHolder -> holder.bind(question)
            is TextViewHolder -> holder.bind(question)
        }
    }

    override fun getItemCount(): Int = questions.size

    inner class CheckboxViewHolder(private val binding: ItemCheckboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.questionTv.text = question.quest
        }
    }

    inner class TextViewHolder(private val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.questionTv.text = question.quest
        }
    }
}
