package com.ichwan.schoolreport.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.R
import com.ichwan.schoolreport.databinding.ItemStudentBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.view.DetailStudentActivity

class ListStudentAdapter(var students: List<User>) : RecyclerView.Adapter<ListStudentAdapter.ListStudentViewHolder>() {

    class ListStudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var studentItem: ItemStudentBinding
        fun bind(student: User) {
            studentItem = ItemStudentBinding.bind(view)
            studentItem.nameTv.text = student.name
            studentItem.classTv.text = student.room

            if (student.gender.equals("boy", ignoreCase = true)) {
                studentItem.avatarImg.setImageResource(R.drawable.icon_boy)
            } else {
                studentItem.avatarImg.setImageResource(R.drawable.icon_girl)
            }

            studentItem.studentItemCv.setOnClickListener{
                val context = view.context
                val intent = Intent(context, DetailStudentActivity::class.java)
                intent.putExtra("regnumber", student.regnumber)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListStudentViewHolder {
        val item = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return ListStudentViewHolder(item)
    }

    override fun onBindViewHolder(
        holder: ListStudentViewHolder,
        position: Int
    ) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}