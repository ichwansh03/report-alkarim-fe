package com.ichwan.schoolreport.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.R
import com.ichwan.schoolreport.databinding.ActivityDetailStudentBinding

class DetailStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        binding.greetingDetailStudentTv.text = getString(R.string.laporan_ananda_ichwan_sholihin, name)

        binding.inputValuationFab.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_valuation_container,
                ValuationFragment()).commit()
        }

    }
}