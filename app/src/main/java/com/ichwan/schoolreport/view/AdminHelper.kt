package com.ichwan.schoolreport.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.ichwan.schoolreport.adapter.SectionsPagerAdapter
import com.ichwan.schoolreport.databinding.ActivityAdminBinding

class AdminHelper(
    private val activity: AppCompatActivity,
    private val binding: ActivityAdminBinding
) {

    private val tabTitles = arrayOf("Users", "Classes")

    fun setupTabs() {
        val sectionsPagerAdapter = SectionsPagerAdapter(activity)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}
