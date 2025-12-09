package com.ichwan.schoolreport.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdminHelper(
    private val activity: AppCompatActivity
) {

    // The adapter now handles three fragments
    val adapter = object : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3 // Increased to 3 tabs

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> UserListFragment.newInstance("STUDENT")
                1 -> UserListFragment.newInstance("TEACHER")
                else -> ClassListFragment.newInstance() // Our new fragment for classes
            }
        }
    }

}
