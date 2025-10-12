package com.ichwan.schoolreport.view

import android.content.Intent
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding

class TeacherController(
    private val activity: MainActivity,
    private val binding: ActivityTeacherBinding
) {
    fun setupView() {
        binding.addQuestionFab.setOnClickListener {
            val intent = Intent(activity, AddQuestionActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
