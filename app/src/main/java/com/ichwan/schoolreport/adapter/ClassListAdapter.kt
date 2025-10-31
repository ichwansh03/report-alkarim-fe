package com.ichwan.schoolreport.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.databinding.ItemClassBinding
import com.ichwan.schoolreport.model.ClassRoom

class ClassListAdapter(private val classList: List<ClassRoom>) : RecyclerView.Adapter<ClassListAdapter.ClassViewHolder>() {

    inner class ClassViewHolder(private val binding: ItemClassBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(classRoom: ClassRoom) {
            binding.className.text = classRoom.name
            binding.studentCount.text = "Jumlah Siswa: ${classRoom.studentTotal}"
            binding.homeroomTeacher.text = "Wali Kelas: ${classRoom.teacher}"

            // Set listener for the delete icon if needed
            binding.deleteIcon.setOnClickListener {
                // Handle delete action
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val binding = ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.bind(classList[position])
    }

    override fun getItemCount(): Int = classList.size
}
