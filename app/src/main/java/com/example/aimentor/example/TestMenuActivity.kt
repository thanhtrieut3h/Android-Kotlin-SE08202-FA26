package com.example.aimentor.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.aimentor.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class TestMenuActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_menu)
        // Ánh xạ view
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.viewPager)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        navigationView = findViewById(R.id.navigationView)

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
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
    private fun setupViewPagerAndBottomNav() {
        val adapter = TestPagerAdapter(this)
        viewPager.adapter = adapter

        // BottomNavigation → ViewPager2
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> viewPager.currentItem = 0
                R.id.nav_category -> viewPager.currentItem = 1
                R.id.nav_quiz -> viewPager.currentItem = 2
                R.id.nav_settings -> viewPager.currentItem = 3
            }
            true
        }

        // ViewPager2 → BottomNavigation
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigation.selectedItemId = R.id.nav_home
                    1 -> bottomNavigation.selectedItemId = R.id.nav_category
                    2 -> bottomNavigation.selectedItemId = R.id.nav_quiz
                    3 -> bottomNavigation.selectedItemId = R.id.nav_settings
                }
            }
        })
    }
    private fun setupDrawerNavigation() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    viewPager.currentItem = 0
                    bottomNavigation.selectedItemId = R.id.nav_home
                }
                R.id.nav_category -> {
                    viewPager.currentItem = 1
                    bottomNavigation.selectedItemId = R.id.nav_category
                }
                R.id.nav_quiz -> {
                    viewPager.currentItem = 2
                    bottomNavigation.selectedItemId = R.id.nav_quiz
                }
                R.id.nav_settings -> {
                    viewPager.currentItem = 3
                    bottomNavigation.selectedItemId = R.id.nav_settings
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
    @SuppressLint("GestureBackNavigation")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}