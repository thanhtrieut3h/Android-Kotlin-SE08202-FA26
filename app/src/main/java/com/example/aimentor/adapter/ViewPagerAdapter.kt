package com.example.aimentor.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aimentor.Fragment.CategoryFragment
import com.example.aimentor.Fragment.HomeFragment
import com.example.aimentor.Fragment.QuizFragment
import com.example.aimentor.Fragment.SettingsFragment
import com.example.aimentor.MainActivity

class ViewPagerAdapter(fragmentActivity: MainActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CategoryFragment()
            2 -> QuizFragment()
            3 -> SettingsFragment()
            else -> HomeFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4;
    }
}