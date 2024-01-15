package com.example.pfootball.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pfootball.adapter.ViewPagerAdapter
import com.example.pfootball.databinding.ActivityMainBinding
import com.example.pfootball.mvvm.StandingViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var intent: Intent
    private lateinit var standingViewModel: StandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        standingViewModel = ViewModelProvider(this)[StandingViewModel::class.java]
        initViewPager()

        intent = getIntent()
        val bundle = intent.extras
        binding.title.text = bundle?.getString("title")
        standingViewModel.selectedItemData = bundle?.getInt("id")

        binding.iconback.setOnClickListener {
            onBackPressed()
        }

    }


    private fun initViewPager() {
        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, standingViewModel)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, pos ->
            when (pos) {
                0 -> tab.text = "MATCHES"
                1 -> tab.text = "NEWS"
                2 -> tab.text = "STANDINGS"
                3 -> tab.text = "STATS"
                else -> tab.text = "PLAYERS"
            }
        }.attach()
    }
}

