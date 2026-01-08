package com.ichwan.schoolreport.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ichwan.schoolreport.view.AddCategoryFragment
import com.ichwan.schoolreport.view.UserListFragment

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserListFragment()
            1 -> AddCategoryFragment()
            else -> UserListFragment()
        }
    }
}