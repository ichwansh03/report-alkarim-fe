package com.ichwan.schoolreport.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.databinding.ActivityAddQuestionBinding

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCategoryBtn.setOnClickListener {
            val addCategoryFragment = AddCategoryFragment()
            addCategoryFragment.show(supportFragmentManager, "AddCategoryFragment")
        }
    }
}