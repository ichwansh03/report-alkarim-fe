package com.ichwan.schoolreport.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.schoolreport.R
import com.ichwan.schoolreport.databinding.ItemStudentBinding
import com.ichwan.schoolreport.model.User

class ListStudentAdapter(
    private val context: Context,
    private var users: MutableList<User>,
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<ListStudentAdapter.ListStudentViewHolder>() {

    inner class ListStudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.nameTv.text = user.name
            binding.classTv.text = user.room
            binding.avatarImg.setImageResource(R.drawable.account_circle) // Menggunakan ikon generik
            binding.root.setOnClickListener { onItemClick(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListStudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(context), parent, false)
        return ListStudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListStudentViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }
}
