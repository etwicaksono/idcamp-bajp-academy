package com.etwicaksono.academy.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etwicaksono.academy.R
import com.etwicaksono.academy.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
        supportActionBar?.elevation = 0f
    }
}