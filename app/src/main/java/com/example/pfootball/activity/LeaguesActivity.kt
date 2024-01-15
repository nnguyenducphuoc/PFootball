package com.example.pfootball.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pfootball.R
import com.example.pfootball.adapter.LeagueTop5Adapter
import com.example.pfootball.databinding.ActivityLeaguesBinding
import com.example.pfootball.models.LeagueTop5

class LeaguesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaguesBinding
    private lateinit var mTop5List: MutableList<LeagueTop5>
    private lateinit var top5Adapter: LeagueTop5Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaguesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mTop5List = ArrayList()
        top5Adapter = LeagueTop5Adapter(mTop5List)
        binding.recyclerview.hasFixedSize()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = top5Adapter

        top5Adapter.onClickItem = {
            val intent = Intent(this, MainActivity::class.java)
            var bundle = Bundle()
            bundle.putInt("id", it.id)
            bundle.putString("title", it.title)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        setDataRv()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDataRv() {
        mTop5List.add(
            LeagueTop5(
                "Premier League",
                R.drawable.premier,
                39
            )
        )
        mTop5List.add(
            LeagueTop5(
                "Laliga",
                R.drawable.laliga, 140

            )
        )
        mTop5List.add(
            LeagueTop5(
                "Bundesliga",
                R.drawable.bundes, 78
            )
        )
        mTop5List.add(
            LeagueTop5(
                "Serie A",
                R.drawable.seriea, 135
            )
        )
        mTop5List.add(
            LeagueTop5(
                "League 1",
                R.drawable.league1, 61
            )
        )
        top5Adapter.notifyDataSetChanged()
    }
}