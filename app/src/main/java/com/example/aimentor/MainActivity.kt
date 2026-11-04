package com.example.aimentor

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.aimentor.adapter.ViewPagerAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var toolbar : MaterialToolbar
    private lateinit var viewPager : ViewPager2
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var navView : NavigationView
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // anh xa view - tim cac phan tu ngoai view
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar      = findViewById(R.id.toolbar)
        viewPager    = findViewById(R.id.viewPager)
        bottomNav    = findViewById(R.id.bottomNavigation)
        navView      = findViewById(R.id.navigationView)
        setupToolbarAndDrawer()
        setupViewPagerAndBottomNav()
        setupDrawerNavigation()
    }
    private fun setupToolbarAndDrawer() {
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color = Color.parseColor("#FF5722")
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
    private fun setupViewPagerAndBottomNav() {
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        // khi bam tung tab ben duoi bottom navigation
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> viewPager.currentItem = 0 // home fragment
                R.id.nav_category -> viewPager.currentItem = 1 // category fragment
                R.id.nav_quiz -> viewPager.currentItem = 2 // quiz fargment
                R.id.nav_settings -> viewPager.currentItem = 3 // settings fragment
            }
            true
        }
        // khi nguoi vuot qua trai - phai man hinh
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> bottomNav.selectedItemId = R.id.nav_home
                    1 -> bottomNav.selectedItemId = R.id.nav_category
                    2 -> bottomNav.selectedItemId = R.id.nav_quiz
                    3 -> bottomNav.selectedItemId = R.id.nav_settings
                }
            }
        })
    }
    private fun setupDrawerNavigation() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_home -> {
                    viewPager.currentItem = 0
                    bottomNav.selectedItemId = R.id.nav_home
                }
                R.id.nav_category -> {
                    viewPager.currentItem = 1
                    bottomNav.selectedItemId = R.id.nav_category
                }
                R.id.nav_author -> {
                    Toast.makeText(this, "Hi you", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "logged out !", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}