package com.ichwan.schoolreport.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ichwan.schoolreport.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        // The adapter now handles three fragments
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3 // Increased to 3 tabs

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> UserListFragment.newInstance("STUDENT")
                    1 -> UserListFragment.newInstance("TEACHER")
                    else -> ClassListFragment.newInstance() // Our new fragment for classes
                }
            }
        }

        viewPager.adapter = adapter

        // The mediator now sets the title for the third tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Siswa"
                1 -> "Guru"
                else -> "Kelas" // Title for the new tab
            }
        }.attach()
    }
}
