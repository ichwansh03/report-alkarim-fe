package com.ichwan.schoolreport.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ItemCheckboxBinding
import com.ichwan.schoolreport.databinding.ItemTextBinding
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.Question
import com.ichwan.schoolreport.model.User
import kotlinx.coroutines.launch

class QuestionActivityAdapter(
    private var questions: List<Question>,
    private val student: User,
    private val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                val binding =
                    ItemCheckboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CheckboxViewHolder(binding)
            }
            VIEW_TYPE_TEXT -> {
                val binding =
                    ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun updateData(newQuestions: List<Question>) {
        this.questions = newQuestions
        notifyDataSetChanged()
    }

    inner class CheckboxViewHolder(private val binding: ItemCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.questionTv.text = question.quest
            binding.questionCb.setOnCheckedChangeListener { _, isChecked ->
                saveAnswer(question, isChecked.toString())
            }
        }
    }

    inner class TextViewHolder(private val binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.questionTv.text = question.quest
            binding.answerEt.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    saveAnswer(question, binding.answerEt.text.toString())
                }
            }
        }
    }

    private fun saveAnswer(question: Question, answer: String) {
        lifecycleScope.launch {
            try {
                val report = ActivityReport(
                    nip = student.regnumber,
                    category = question.category,
                    question = question.quest,
                    answer = answer,
                    score = ""
                )
                ApiClient.instance.createReport(report)
            } catch (e: Exception) {
                Log.e("SaveAnswer", "Failed to fetch questions: ", e)
            }
        }
    }
}
