package com.ichwan.schoolreport.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemStudentBinding
import com.ichwan.schoolreport.model.Student
import com.ichwan.schoolreport.view.DetailStudentActivity

class ListStudentAdapter(var students: Array<Student>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ListStudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var studentItem: ItemStudentBinding
        fun bind(student: Student) {
            studentItem = ItemStudentBinding.bind(view)
            studentItem.nameTv.text = student.name
            studentItem.classTv.text = student.className
            studentItem.studentItemCv.setOnClickListener{
                val context = view.context
                val intent = Intent(context, DetailStudentActivity::class.java)
                intent.putExtra("name", student.name)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val item = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return ListStudentViewHolder(item)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ListStudentViewHolder).bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}