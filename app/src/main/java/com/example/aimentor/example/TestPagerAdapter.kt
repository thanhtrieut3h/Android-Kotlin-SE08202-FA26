package com.example.aimentor.example

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TestPagerAdapter(fragmentActivity: TestMenuActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeTestFragment()
            1 -> CategoryTestFragment()
            2 -> QuizTestFragment()
            3 -> SettingsTestFragment()
            else -> HomeTestFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

}